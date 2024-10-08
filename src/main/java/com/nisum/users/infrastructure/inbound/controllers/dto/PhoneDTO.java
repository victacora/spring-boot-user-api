package com.nisum.users.infrastructure.inbound.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneDTO {
	private Long id;
	@NotBlank(message = "El número es invalido")
	private String number;
	@NotBlank(message = "El código de ciudad es invalido")
	private String cityCode;
	@NotBlank(message = "El código de país es invalido")
	private String countryCode;
	private Date createdDate;
	private Date updatedDate;
}
