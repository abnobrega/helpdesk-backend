package com.abnobrega.helpdesk.service.exceptions;

public class ObjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    //*************************************************
    //************** C O N S T R U T O R **************
    //*************************************************
	public ObjectNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectNotFoundException(String message) {
		super(message);
	}
	
	

}
