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


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Phone {
	@NotNull
	private Long id;
	@NotBlank
	private String number;
	@NotBlank
	private String cityCode;
	@NotBlank
	private String countryCode;
	@NotNull
	@PastOrPresent
	private Date createdDate;
	@NotNull
	@FutureOrPresent
	private Date updatedDate;

}
