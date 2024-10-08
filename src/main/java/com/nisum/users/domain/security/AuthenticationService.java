package com.nisum.users.domain.security;

import com.nisum.users.domain.model.User;

public interface AuthenticationService {
	String passwordEncoder(String password);
	String token(User user);
}
