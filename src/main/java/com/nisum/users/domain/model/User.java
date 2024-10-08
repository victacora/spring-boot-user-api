package com.nisum.users.domain.model;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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
public class User {
	@NotNull
	private UUID id;
	@NotBlank
	private String name;
	@NotBlank
	private String email;
	@NotBlank
	private String password;
	@NotNull
	@PastOrPresent
	private Date lastLogin;
	private String token;
	private UserStatus isActive;
	@NotNull
	@PastOrPresent
	private Date createdDate;
	@NotNull
	@FutureOrPresent
	private Date updatedDate;
	private List<Phone> phoneList;

}
