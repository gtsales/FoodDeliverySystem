package dev.luiz.oauth.system.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import dev.luiz.oauth.system.dtos.OauthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService{

	private final WebClient webClient;

	@Override
	public OauthResponse loadUserByUsername(String cpf) throws UsernameNotFoundException {
		
		log.debug("Starting find user by cpf {}", cpf);

		try {

			ResponseEntity<OauthResponse> oauthResponse = webClient.get()
		            .uri(uriBuilder -> uriBuilder
		                    .path("/user/oauth")
		                    .queryParam("cpf", cpf)
		                    .build())
		            .retrieve()
		            .toEntity(OauthResponse.class)
		            .block();

			if (oauthResponse != null && oauthResponse.getStatusCode().is2xxSuccessful() && oauthResponse.getBody() != null) {

				return oauthResponse.getBody();
			}
		} catch (Exception e) {

			log.error("Error while trying to find user by cpf {}. ERROR: {} ", cpf, e.getMessage());
		}

		log.debug("Finishing find user by cpf {}", cpf);

		return null;
	}
}