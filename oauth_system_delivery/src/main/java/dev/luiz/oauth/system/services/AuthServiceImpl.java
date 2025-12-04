package dev.luiz.oauth.system.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.web.authentication.www.BasicAuthenticationConverter;
import org.springframework.stereotype.Service;

import dev.luiz.oauth.system.dtos.AuthResponse;
import dev.luiz.oauth.system.interfaces.AuthService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService{
	
    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
	private HttpServletRequest httpServletRequest;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Override
    public AuthResponse createAuthenticationToken() {
    	
        log.debug("Starting generate token");

        String[] credentials = decodeCredentials();
        String cpf = credentials[0];
        String password = credentials[1];

        Authentication authentication = authenticate(cpf, password);

        log.debug("Generate token finished");
        
        return generateJwtToken(authentication);
    }

    private String[] decodeCredentials() {
        
        log.debug("Starting decode credentials");

        BasicAuthenticationConverter converter = new BasicAuthenticationConverter();
        Authentication authentication = converter.convert(httpServletRequest);

        if (authentication instanceof UsernamePasswordAuthenticationToken) {
        	
            UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
            return new String[]{token.getName(), String.valueOf(token.getCredentials())};
        }

        throw new IllegalArgumentException("Invalid Basic Auth header");
    }

    private Authentication authenticate(String cpf, String password) {
    	
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(cpf, password)
        );
    }
    
    private AuthResponse generateJwtToken(Authentication authentication) {
    	
    	log.debug("Starting generate JwtToken");
    	
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Instant now = Instant.now();
        
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(10, ChronoUnit.MINUTES))
                .subject(userDetails.getUsername())
                .claim("scope", "ROLE_USER")
                .build();

        log.debug("Generate JwtToken finished");
        
        return new AuthResponse(jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue(), claims.getExpiresAt());
    }
}
