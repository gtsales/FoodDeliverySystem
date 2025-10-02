package dev.luiz.user.system.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EnderecoDto {

	private String rua;
	private String numero;
	private String cidade;
	private String estado;
	private String cep;
}
