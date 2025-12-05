package dev.luiz.customer.system.controllers;

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

import dev.luiz.customer.system.apis.CustomerApi;
import dev.luiz.customer.system.dtos.GetCustomerResponseDto;
import dev.luiz.customer.system.dtos.OauthResponse;
import dev.luiz.customer.system.dtos.RegisterCustomerRequestDto;
import dev.luiz.customer.system.interfaces.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class CustomerController implements CustomerApi{

	private final CustomerService userService;
	
	@Override
	@GetMapping("/find")
	public ResponseEntity<GetCustomerResponseDto> getUser(@RequestParam String cpf) {
		
		log.trace("Starting get user.");
		
		return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByCpf(cpf));
	}

	@Override
	@PostMapping("/register")
	public ResponseEntity<Void> registerUser(@Valid @RequestBody RegisterCustomerRequestDto registerUserRequestDto) {
		
		log.trace("Starting register user.");
		
		userService.registerUser(registerUserRequestDto);
		
		log.trace("Finishing register user.");
		
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}

	@Override
	@DeleteMapping("/delete")
	public ResponseEntity<Void> deleteUser(@RequestParam String cpf) {
		
		log.trace("Starting delete user.");
		
		userService.deleteUserByCpf(cpf);
		
		log.trace("Finishing delete user.");
		
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@Override
	@GetMapping("/oauth")
	public ResponseEntity<OauthResponse> getOauthCredentials(@RequestParam String cpf) {
		
		log.trace("Starting get oauth credentials.");
		
		return ResponseEntity.status(HttpStatus.OK).body(userService.findCredentials(cpf));
	}
}
