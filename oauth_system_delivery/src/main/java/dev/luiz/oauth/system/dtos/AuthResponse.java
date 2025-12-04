package dev.luiz.oauth.system.dtos;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {

	private String token;
    private Instant expirationTime;
}
