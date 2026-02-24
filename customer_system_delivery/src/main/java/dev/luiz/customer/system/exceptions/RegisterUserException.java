package dev.luiz.customer.system.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class RegisterUserException extends RuntimeException{

	private static final long serialVersionUID = 1L;

    @Getter
    private String message;
}
