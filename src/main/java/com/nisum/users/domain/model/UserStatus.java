package com.nisum.users.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserStatus {
	INACTIVE, ACTIVE;
}