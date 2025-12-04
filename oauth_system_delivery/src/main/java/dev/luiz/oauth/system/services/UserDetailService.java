package dev.luiz.oauth.system.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.luiz.oauth.system.dtos.OauthResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailService implements UserDetailsService{

	@Override
	public OauthResponse loadUserByUsername(String cpf) throws UsernameNotFoundException {
		
		log.debug("Starting find user by cpf {}", cpf);

//		try {
//
//			ResponseEntity<UserHrOauthResponse> userHrOauthResponseEntity = userFeignClient.findByEmail(email);
//
//			if (userHrOauthResponseEntity != null && userHrOauthResponseEntity.getStatusCode() == HttpStatus.OK) {
//
//				return userHrOauthResponseEntity.getBody();
//			}
//		} catch (Exception e) {
//
//			log.error("Error while trying to find user by email {}. ERROR: {} ", email, e.getMessage());
//		}

		log.debug("Finishing find user by cpf {}", cpf);

		return null;
	}
}
