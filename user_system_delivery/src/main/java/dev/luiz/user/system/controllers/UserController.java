package dev.luiz.user.system.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.luiz.user.system.apis.UserApi;
import dev.luiz.user.system.dtos.GetUserResponseDto;
import dev.luiz.user.system.dtos.RegisterUserRequestDto;
import dev.luiz.user.system.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController implements UserApi{

	private final UserService userService;
	
	@Override
	@GetMapping("/cpf")
	public ResponseEntity<GetUserResponseDto> getUser(@RequestParam String cpf) {
		
		return null;
	}

	@Override
	@PostMapping("/register")
	public ResponseEntity<Void> registerUser(@Valid @RequestBody RegisterUserRequestDto registerUserRequestDto) {
		
		log.trace("Starting register user.");
		
		userService.registerUser(registerUserRequestDto);
		
		log.trace("Finishing register user.");
		
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}

	@Override
	@DeleteMapping("/delete")
	public ResponseEntity<Void> deleteUser(@RequestParam String cpf) {
		
		log.trace("Starting delete user.");
		
		userService.deleteUser(cpf);
		
		log.trace("Finishing delete user.");
		
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
}
