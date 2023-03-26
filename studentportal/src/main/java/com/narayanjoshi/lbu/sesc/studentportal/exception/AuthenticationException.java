package com.narayanjoshi.lbu.sesc.studentportal.exception;

public class AuthenticationException extends org.springframework.security.core.AuthenticationException {

	public AuthenticationException(String usernamee) {
		super("Credential does not match.");
	}

	public AuthenticationException() {
		super("User does not exist. Please register");
	}
}
