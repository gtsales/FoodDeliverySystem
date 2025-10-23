package dev.luiz.user.system.apis;

import org.springframework.http.ResponseEntity;

import dev.luiz.user.system.dtos.GetUserResponseDto;
import dev.luiz.user.system.dtos.RegisterUserRequestDto;

public interface UserApi {

	ResponseEntity<GetUserResponseDto> getUser(String cpf);
	ResponseEntity<Void> registerUser(RegisterUserRequestDto registerUserRequestDto);
	ResponseEntity<Void> deleteUser(String cpf);
}
