package com.nisum.users.infrastructure.outbound.database.repository;

import com.nisum.users.infrastructure.outbound.database.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepositoryJPA extends JpaRepository<UserEntity, UUID> {
	@Query(value = "SELECT * FROM \"user\" u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :keyword,'%')) OR LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword,'%'))", nativeQuery = true)
	List<UserEntity> findByNameOrEmail(String keyword);
	Optional<UserEntity> findByEmail(String email);
}
