package com.nisum.users.infrastructure.outbound.database.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Data
@Table(name = PhoneEntity.PHONE)
public class PhoneEntity {
	static final String PHONE = "PHONE";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String number;
	@NotBlank
	private String cityCode;
	@NotBlank
	private String countryCode;
	@PastOrPresent
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;
	@FutureOrPresent
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updatedDate;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private UserEntity user;
}