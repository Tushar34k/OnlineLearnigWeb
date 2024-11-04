package com.learning.platform.customexception;

public class EnrollmentException extends RuntimeException {

	private String message;

	public EnrollmentException(String message) {
		super(message);

	}

}
