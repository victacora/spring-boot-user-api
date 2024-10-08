package com.nisum.users.infrastructure.outbound.database.entities;

import com.nisum.users.domain.model.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = UserEntity.USER)
public class UserEntity {
	static final String USER = "\"USER\"";
	@Id
	@UuidGenerator
	private UUID id;
	@NotBlank
	private String name;
	@NotBlank
	private String email;
	@NotBlank
	private String password;
	@PastOrPresent
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date lastLogin;
	private String token;
	@Enumerated(EnumType.ORDINAL)
	private UserStatus isActive;
	@PastOrPresent
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;
	@FutureOrPresent
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updatedDate;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<PhoneEntity> phoneList;
}