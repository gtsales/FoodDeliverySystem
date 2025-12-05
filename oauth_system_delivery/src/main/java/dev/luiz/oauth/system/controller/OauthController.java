package dev.luiz.oauth.system.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.luiz.oauth.system.apis.OauthControllerApi;
import dev.luiz.oauth.system.dtos.AuthResponse;
import dev.luiz.oauth.system.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/oauth")
@RequiredArgsConstructor
public class OauthController implements OauthControllerApi{

	private final AuthService authService;
	
	@Override
	@PostMapping(value = "/token")
    public ResponseEntity<AuthResponse> generateToken() {
    	
    	log.debug("Generate Token started.");
    	
    	AuthResponse authResponse = authService.createAuthenticationToken();

    	log.debug("Generate Token finished.");
        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
    }
}
