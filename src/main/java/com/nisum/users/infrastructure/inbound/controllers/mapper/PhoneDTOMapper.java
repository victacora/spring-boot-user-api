package com.nisum.users.infrastructure.inbound.controllers.mapper;

import com.nisum.users.domain.model.Phone;
import com.nisum.users.infrastructure.inbound.controllers.dto.PhoneDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PhoneDTOMapper {
	PhoneDTO toDTO(Phone phone);

	Phone toDomain(PhoneDTO phoneDTO);
}
