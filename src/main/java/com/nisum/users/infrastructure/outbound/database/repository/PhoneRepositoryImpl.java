package com.nisum.users.infrastructure.outbound.database.repository;

import com.nisum.users.domain.model.Phone;
import com.nisum.users.domain.repository.PhoneRepository;
import com.nisum.users.infrastructure.outbound.database.entities.PhoneEntity;
import com.nisum.users.infrastructure.outbound.database.mapper.PhoneEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PhoneRepositoryImpl implements PhoneRepository {
	private final PhoneRepositoryJPA phoneRepositoryJPA;
	private final PhoneEntityMapper phoneEntityMapper;

	@Autowired
	public PhoneRepositoryImpl(PhoneEntityMapper phoneEntityMapper, PhoneRepositoryJPA phoneRepositoryJPA) {
		this.phoneEntityMapper = phoneEntityMapper;
		this.phoneRepositoryJPA = phoneRepositoryJPA;
	}

	@Override
	public Optional<Phone> createPhone(Phone phone) {
		PhoneEntity PhoneEntity = phoneEntityMapper.toEntity(phone);
		phoneRepositoryJPA.save(PhoneEntity);
		return Optional.empty();
	}

	@Override
	public Optional<Phone> updatePhone(Long id, Phone phone) {

		return Optional.empty();
	}

	@Override
	public void deletePhone(Long id) {
		phoneRepositoryJPA.deleteById(id);
	}

	@Override
	public Optional<Phone> findById(Long id) {
		Optional<PhoneEntity> entity = phoneRepositoryJPA.findById(id);
		if (entity.isEmpty()) {
			return Optional.empty();
		}
		Phone phone = phoneEntityMapper.toDomain(entity.get());
		return Optional.of(phone);
	}

	@Override
	public List<Phone> findByNumber(String number) {
		return null;
	}

	@Override
	public List<Phone> findAllPhones() {
		return null;
	}
}
