package com.nisum.users.infrastructure.inbound.controllers.security;

import com.nisum.users.domain.model.User;
import com.nisum.users.domain.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	private final JwtService jwtService;

	@Autowired
	public AuthenticationServiceImpl(
			JwtService jwtService
	) {
		this.jwtService = jwtService;
	}

	@Override
	public String passwordEncoder(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}

	@Override
	public String token(User user) {
		return jwtService.generateToken(user);
	}

}
