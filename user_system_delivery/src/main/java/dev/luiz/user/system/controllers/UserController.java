package dev.luiz.user.system.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.luiz.user.system.apis.UserApi;
import dev.luiz.user.system.dtos.GetUserResponseDto;
import dev.luiz.user.system.dtos.RegisterUserRequestDto;

@RestController
@RequestMapping("/user")
public class UserController implements UserApi{

	@Override
	@GetMapping("/cpf")
	public ResponseEntity<GetUserResponseDto> getUser(@RequestParam String cpf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@PostMapping("/register")
	public ResponseEntity<Void> registerUser(@RequestBody RegisterUserRequestDto registerUserRequestDto) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
}
