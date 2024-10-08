package com.nisum.users.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.nisum.users.domain.exceptions.UserEmailDuplicatedException;
import com.nisum.users.domain.security.AuthenticationService;
import com.nisum.users.domain.repository.UserRepository;
import com.nisum.users.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserUseCaseTest {
	@Mock
	private UserRepository userRepository;
	@Mock
	private AuthenticationService authenticationService;
	@InjectMocks
	private UserUseCase userUseCase;
	private User user;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		user = new User();
		user.setEmail("juan@rodriguez.org");
		user.setPassword("hunter2");
	}

	@Test
	void testCreateUser_Success() {
		// Arrange
		when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
		when(authenticationService.passwordEncoder(user.getPassword())).thenReturn("encodedPassword");
		when(authenticationService.token(user)).thenReturn("generatedToken");
		when(userRepository.createUser(any(User.class))).thenReturn(Optional.of(user));

		// Act
		Optional<User> result = userUseCase.createUser(user);

		// Assert
		assertTrue(result.isPresent());
		assertEquals("encodedPassword", result.get().getPassword());
		assertEquals("generatedToken", result.get().getToken());
		verify(userRepository).createUser(any(User.class));
	}

	@Test
	void testCreateUser_EmailAlreadyExists() {
		// Arrange
		when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

		// Act & Assert
		UserEmailDuplicatedException exception = assertThrows(UserEmailDuplicatedException.class, () -> {
			userUseCase.createUser(user);
		});
		assertEquals("El correo ya esta registrado", exception.getMessage());
		verify(userRepository, never()).createUser(any(User.class)); // Ensure createUser is never called
	}
}