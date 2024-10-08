package com.nisum.users.domain.exceptions;

public class InvalidDataException extends RuntimeException {
	public InvalidDataException(String message) {
		super(message);
	}
}
