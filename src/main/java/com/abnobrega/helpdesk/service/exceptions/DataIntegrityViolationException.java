package com.abnobrega.helpdesk.service.exceptions;

public class DataIntegrityViolationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    //*************************************************
    //************** C O N S T R U T O R **************
    //*************************************************
	public DataIntegrityViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataIntegrityViolationException(String message) {
		super(message);
	}
	
}
