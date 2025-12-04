package dev.luiz.oauth.system.interfaces;

import dev.luiz.oauth.system.dtos.AuthResponse;

public interface AuthService {

	AuthResponse createAuthenticationToken();
}
