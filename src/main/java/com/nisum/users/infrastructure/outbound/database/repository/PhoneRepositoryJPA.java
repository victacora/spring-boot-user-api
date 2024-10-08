package com.nisum.users.infrastructure.outbound.database.repository;

import com.nisum.users.infrastructure.outbound.database.entities.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepositoryJPA extends JpaRepository<PhoneEntity, Long> {
	List<PhoneEntity> findByNumber(String number);
}
