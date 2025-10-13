package dev.luiz.user.system.dtos;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Cpf can not be null or empty")
	private String cpf;
	
	@NotBlank(message = "Nome can not be null or empty")
	private String nome;
	
	@NotBlank(message = "Email can not be null or empty")
	private String email;
	
	@NotBlank(message = "Senha can not be null or empty")
	private String senha;
	
	@Valid
	private EnderecoDto enderecoDto;
}
