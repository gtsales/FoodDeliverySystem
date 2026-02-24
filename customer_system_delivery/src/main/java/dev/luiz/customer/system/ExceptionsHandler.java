package dev.luiz.customer.system;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import dev.luiz.customer.system.exceptions.RegisterUserException;
import dev.luiz.customer.system.exceptions.response.RegisterUserExceptionResponse;

@ControllerAdvice
public class ExceptionsHandler extends ExceptionHandlerExceptionResolver {

	@ExceptionHandler(RegisterUserException.class)
	public ResponseEntity<Object> registerUserExceptionHandler(RegisterUserException ex){
		
		HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
		
		return new ResponseEntity<>(new RegisterUserExceptionResponse(ex.getMessage()), httpStatus);
	}
}
