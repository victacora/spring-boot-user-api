package com.nisum.users.infrastructure.outbound.database.repository;

import com.nisum.users.domain.model.User;
import com.nisum.users.domain.repository.UserRepository;
import com.nisum.users.infrastructure.outbound.database.entities.UserEntity;
import com.nisum.users.infrastructure.outbound.database.mapper.UserEntityMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
	private final UserRepositoryJPA userRepositoryJPA;
	private final UserEntityMapper userEntityMapper;

	@Autowired
	public UserRepositoryImpl(UserEntityMapper userEntityMapper, UserRepositoryJPA userRepositoryJPA) {
		this.userEntityMapper = userEntityMapper;
		this.userRepositoryJPA = userRepositoryJPA;
	}

	@Override
	@Transactional
	public Optional<User> createUser(User user) {
		return saveUser(user);
	}

	@Override
	@Transactional
	public Optional<User> updateUser(UUID id,User user) {
		if (userRepositoryJPA.existsById(id)) {
			return saveUser(user);
		}
		return Optional.empty();
	}

	private Optional<User> saveUser(User user) {
		UserEntity userEntity = userEntityMapper.toEntity(user);
		userEntity = userRepositoryJPA.saveAndFlush(userEntity);
		return Optional.of(userEntityMapper.toDomain(userEntity));
	}

	@Override
	@Transactional
	public void deleteUser(UUID id) {
		userRepositoryJPA.deleteById(id);
	}

	@Override
	public Optional<User> findById(UUID id) {
		Optional<UserEntity> entity = userRepositoryJPA.findById(id);
		if (entity.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(userEntityMapper.toDomain(entity.get()));
	}

	@Override
	public Optional<User> findByEmail(String email) {
		Optional<UserEntity> entity = userRepositoryJPA.findByEmail(email);
		if (entity.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(userEntityMapper.toDomain(entity.get()));
	}

	@Override
	public List<User> searchBy(String keyWord) {
		return userRepositoryJPA.findByNameOrEmail(keyWord).stream().map(userEntityMapper::toDomain)
				.collect(Collectors.toUnmodifiableList());
	}

	@Override
	public List<User> findAllUsers() {
		return userRepositoryJPA.findAll().stream().map(userEntityMapper::toDomain)
				.collect(Collectors.toUnmodifiableList());
	}
}
