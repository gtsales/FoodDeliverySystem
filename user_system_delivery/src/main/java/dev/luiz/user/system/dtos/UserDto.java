package dev.luiz.user.system.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {

	private Long id;
	private String cpf;
	private String nome;
	private String email;
	private String senha;
	private EnderecoDto enderecoDto;
}
