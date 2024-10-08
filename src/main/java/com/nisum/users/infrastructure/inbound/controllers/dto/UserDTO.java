package com.nisum.users.infrastructure.inbound.controllers.dto;

import com.nisum.users.domain.model.UserStatus;
import com.nisum.users.infrastructure.inbound.controllers.validators.DynamicPattern;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
	private UUID id;
	@NotBlank(message = "El nombre no puede estar vacio")
	private String name;
	@DynamicPattern(regexKey = "email.regex", message = "El correo no tiene el formato correcto")
	@NotBlank(message = "El correo no puede estar vacio")
	private String email;
	@DynamicPattern(regexKey = "password.regex", message = "La contrasena no tiene el formato correcto")
	@NotBlank(message = "La contrasena no puede estar vacia")
	private String password;
	private Date lastLogin;
	private String token;
	private UserStatus isActive;
	private Date createdDate;
	private Date updatedDate;
	private List<PhoneDTO> phoneList;
}
