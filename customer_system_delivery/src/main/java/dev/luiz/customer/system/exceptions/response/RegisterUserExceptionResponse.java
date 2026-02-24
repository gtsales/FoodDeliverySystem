package dev.luiz.customer.system.exceptions.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterUserExceptionResponse implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
	private String message;
}
