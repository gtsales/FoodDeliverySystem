package dev.luiz.customer.system.apis;

import org.springframework.http.ResponseEntity;

import dev.luiz.customer.system.dtos.GetCustomerResponseDto;
import dev.luiz.customer.system.dtos.OauthResponse;
import dev.luiz.customer.system.dtos.RegisterCustomerRequestDto;

public interface CustomerApi {

	ResponseEntity<GetCustomerResponseDto> getUser(String cpf);
	ResponseEntity<Void> registerUser(RegisterCustomerRequestDto registerUserRequestDto);
	ResponseEntity<Void> deleteUser(String cpf);
	ResponseEntity<OauthResponse> getOauthCredentials(String cpf);
}
