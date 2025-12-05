package dev.luiz.customer.system.interfaces;

import dev.luiz.customer.system.dtos.GetCustomerResponseDto;
import dev.luiz.customer.system.dtos.OauthResponse;
import dev.luiz.customer.system.dtos.RegisterCustomerRequestDto;

public interface CustomerService {

	void registerUser(RegisterCustomerRequestDto registerUserRequestDto);
	void deleteUserByCpf(String cpf);
	GetCustomerResponseDto findUserByCpf(String cpf);
	OauthResponse findCredentials(String cpf);
}
