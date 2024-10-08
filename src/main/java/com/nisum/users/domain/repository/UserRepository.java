package com.nisum.users.domain.repository;

import com.nisum.users.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
	Optional<User> createUser(User user);
	Optional<User> updateUser(UUID id, User user);
	void deleteUser(UUID id);
	Optional<User> findById(UUID id);
	Optional<User> findByEmail(String email);
	List<User> searchBy(String keyWord);
	List<User> findAllUsers();
}