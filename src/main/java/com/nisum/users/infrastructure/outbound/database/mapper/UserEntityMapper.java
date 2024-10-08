package com.nisum.users.infrastructure.outbound.database.mapper;

import com.nisum.users.domain.model.User;
import com.nisum.users.infrastructure.outbound.database.entities.UserEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = PhoneEntityMapper.class)
public interface UserEntityMapper {
	UserEntity toEntity(User user);

	User toDomain(UserEntity userEntity);

	@AfterMapping
	default void setUserOnPhones(@MappingTarget UserEntity userEntity) {
		userEntity.getPhoneList().forEach(phone -> phone.setUser(userEntity));
	}
}
