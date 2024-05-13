package com.example.bookstoreapi.utils.exceptions;

import org.springframework.security.core.AuthenticationException;

public class InvalidJwtAuthenticationException extends AuthenticationException {

	public InvalidJwtAuthenticationException(String message) {
		super(message);
	}
	
}