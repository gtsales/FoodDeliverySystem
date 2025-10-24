package dev.luiz.user.system.interfaces;

import dev.luiz.user.system.dtos.GetUserResponseDto;
import dev.luiz.user.system.dtos.RegisterUserRequestDto;

public interface UserService {

	void registerUser(RegisterUserRequestDto registerUserRequestDto);
	void deleteUserByCpf(String cpf);
	GetUserResponseDto findUserByCpf(String cpf);
}
