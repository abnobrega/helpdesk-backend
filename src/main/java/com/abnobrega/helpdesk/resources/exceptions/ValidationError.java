package com.abnobrega.helpdesk.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	//OBS: Por herança, esta classe herda todos os atributos e métodos da StandardError.
	// *************************
	// ******* ATRIBUTOS *******
	// *************************
	private List<FieldMessage> errors = new ArrayList<>();

	// **************************
	// ******* CONSTRUTOR *******
	// **************************	
	public ValidationError() {
		super();
	}

	public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	//*************************
	//*******  MÉTODOS  *******
	//*************************	
	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void setErrors(String fieldName, String message) {
		this.errors.add(new FieldMessage(fieldName, message));
	}
	
}
