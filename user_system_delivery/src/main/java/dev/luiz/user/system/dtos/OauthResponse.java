package dev.luiz.user.system.dtos;

import dev.luiz.user.system.enums.UserType;
import dev.luiz.user.system.models.User;
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
	private UserType userType;
	
	public static OauthResponse from(User user) {
		
		return new OauthResponse(user.getCpf(), user.getSenha(), UserType.CLIENTE);
	}
}
