package com.nisum.users.infrastructure.outbound.database.mapper;

import com.nisum.users.domain.model.Phone;
import com.nisum.users.infrastructure.outbound.database.entities.PhoneEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PhoneEntityMapper {
	PhoneEntity toEntity(Phone user);

	Phone toDomain(PhoneEntity userEntity);
}
