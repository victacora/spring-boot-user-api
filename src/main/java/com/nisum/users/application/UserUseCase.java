package com.nisum.users.application;

import com.nisum.users.domain.exceptions.UserEmailDuplicatedException;
import com.nisum.users.domain.exceptions.UserNotFoundException;
import com.nisum.users.domain.model.User;
import com.nisum.users.domain.model.UserStatus;
import com.nisum.users.domain.repository.UserRepository;
import com.nisum.users.domain.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserUseCase {
	private final UserRepository userRepository;
	private final AuthenticationService authenticationService;
	public Optional<User> createUser(User user) {
		if (this.userRepository.findByEmail(user.getEmail()).isPresent()) {
			throw new UserEmailDuplicatedException("El correo ya esta registrado");
		}
		user.setIsActive(UserStatus.ACTIVE);
		user.setPassword(authenticationService.passwordEncoder(user.getPassword()));
		user.setToken(authenticationService.token(user));
		return this.userRepository.createUser(user);
	}

	public Optional<User> updateUser(UUID id, User user) {
		return this.userRepository.updateUser(id, user);
	}

	public void deleteUser(UUID userId) {
		this.userRepository.deleteUser(userId);
	}

	public List<User> findAllUsers() {
		return this.userRepository.findAllUsers();
	}

	public User findById(UUID id) {
		return this.userRepository.findById(id).orElseThrow();
	}

	public User findByEmail(String email) {
		return this.userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("No se encontro un usuario asociado al email"));
	}

}
