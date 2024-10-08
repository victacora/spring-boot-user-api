package com.nisum.users.domain.exceptions;

public class UserEmailDuplicatedException extends RuntimeException {
	public UserEmailDuplicatedException(String message) {
		super(message);
	}
}
