package dev.luiz.user.system.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EnderecoDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Rua can not be null or empty")
	private String rua;
	
	@NotBlank(message = "Número can not be null or empty")
	private String numero;
	
	@NotBlank(message = "Cidade can not be null or empty")
	private String cidade;
	
	@NotBlank(message = "Estado can not be null or empty")
	private String estado;
	
	@NotBlank(message = "Cep can not be null or empty")
	private String cep;
}
