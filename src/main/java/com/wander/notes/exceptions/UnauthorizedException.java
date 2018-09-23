package com.wander.notes.exceptions;

public class UnauthorizedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UnauthorizedException(String errorMessage, Throwable err) {
	    super(errorMessage, err);
	}
	
	public UnauthorizedException(String errorMessage) {
	    super(errorMessage);
	}

}
