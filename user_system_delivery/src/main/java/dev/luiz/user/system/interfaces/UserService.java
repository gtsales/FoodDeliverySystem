package dev.luiz.user.system.interfaces;

import dev.luiz.user.system.dtos.RegisterUserRequestDto;

public interface UserService {

	void registerUser(RegisterUserRequestDto registerUserRequestDto);
}
