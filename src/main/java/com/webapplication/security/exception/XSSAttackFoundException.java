package com.webapplication.security.exception;

public class XSSAttackFoundException extends RuntimeException {

	private static final long serialVersionUID = 4351346435243741514L;
	String errorMessage = "Invalid Input";

	@Override
	public String getMessage() {
		return errorMessage;
	}
}
