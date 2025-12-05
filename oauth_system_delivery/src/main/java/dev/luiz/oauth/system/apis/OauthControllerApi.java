package dev.luiz.oauth.system.apis;

import org.springframework.http.ResponseEntity;

import dev.luiz.oauth.system.dtos.AuthResponse;

public interface OauthControllerApi {

	public ResponseEntity<AuthResponse> generateToken();
}
