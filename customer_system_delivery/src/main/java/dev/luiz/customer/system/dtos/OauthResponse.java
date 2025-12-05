package dev.luiz.customer.system.dtos;

import dev.luiz.customer.system.enums.CustomerType;
import dev.luiz.customer.system.models.Customer;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class OauthResponse {
	
	@Getter
	private String cpf;
	
	@Getter
	private String password;
	
	@Getter
	private CustomerType userType;
	
	public static OauthResponse from(Customer user) {
		
		return new OauthResponse(user.getCpf(), user.getSenha(), CustomerType.CLIENTE);
	}
}
