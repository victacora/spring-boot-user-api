package com.nisum.users.infrastructure.inbound.controllers.exceptions;

import com.nisum.users.domain.exceptions.BadRequestException;
import com.nisum.users.domain.exceptions.InvalidDataException;
import com.nisum.users.domain.exceptions.UserEmailDuplicatedException;
import com.nisum.users.domain.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDTO handleException(Exception exception) {
		return ErrorDTO.builder()
				.message(exception.getLocalizedMessage())
				.build();
	}

	@ResponseBody
	@ExceptionHandler({
			BadRequestException.class,
			InvalidDataException.class,
			UserEmailDuplicatedException.class,
			UserNotFoundException.class
	})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDTO handleCustomExceptions(Exception exception) {
		return ErrorDTO.builder()
				.message(exception.getLocalizedMessage())
				.build();
	}

	@ResponseBody
	@ExceptionHandler({
			MethodArgumentNotValidException.class
	})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDTO handleBadRequestException(MethodArgumentNotValidException exception) {
		return ErrorDTO.builder()
				.message(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage())
				.build();
	}

}