package com.nisum.users.domain.exceptions;

public class BadRequestException extends RuntimeException {
	public BadRequestException(String message) {
		super(message);
	}
}
