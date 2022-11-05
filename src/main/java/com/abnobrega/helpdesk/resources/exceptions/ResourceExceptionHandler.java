package com.abnobrega.helpdesk.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.abnobrega.helpdesk.service.exceptions.DataIntegrityViolationException;
import com.abnobrega.helpdesk.service.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	// Um tratador de exceções da classe ObjectNotFoundException	
	@ExceptionHandler(ObjectNotFoundException.class) 
	public ResponseEntity<StandardError> objectNotFoundException(
			ObjectNotFoundException ex, 
			HttpServletRequest request) {
		
		StandardError error = new StandardError(System.currentTimeMillis(), 
												HttpStatus.NOT_FOUND.value(), 
												"Object Not Found", 
												ex.getMessage(), 
												request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
	}
	
	// Um tratador de exceções da classe DataIntegrityViolationException
	@ExceptionHandler(DataIntegrityViolationException.class) 
	public ResponseEntity<StandardError> dataIntegrityViolationException(
			DataIntegrityViolationException ex, 
			HttpServletRequest request) {
		
		StandardError error = new StandardError(System.currentTimeMillis(), 
												HttpStatus.BAD_REQUEST.value(), 
												"Violação da Integridade de dados", 
												ex.getMessage(), 
												request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
	}	

}
