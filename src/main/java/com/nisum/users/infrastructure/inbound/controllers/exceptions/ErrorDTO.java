package com.nisum.users.infrastructure.inbound.controllers.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO {
	private String message;
}
