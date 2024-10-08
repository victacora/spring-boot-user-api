package com.nisum.users.domain.repository;

import com.nisum.users.domain.model.Phone;

import java.util.List;
import java.util.Optional;

public interface PhoneRepository {
	Optional<Phone> createPhone(Phone Phone);
	Optional<Phone> updatePhone(Long id, Phone Phone);
	void deletePhone(Long id);
	Optional<Phone> findById(Long id);
	List<Phone> findByNumber(String number);
	List<Phone> findAllPhones();
}