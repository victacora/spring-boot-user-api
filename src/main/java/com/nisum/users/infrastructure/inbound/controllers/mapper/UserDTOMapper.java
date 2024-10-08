package com.nisum.users.infrastructure.inbound.controllers.mapper;

import com.nisum.users.domain.model.User;
import com.nisum.users.infrastructure.inbound.controllers.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = PhoneDTOMapper.class)
public interface UserDTOMapper {
	UserDTO toDTO(User user);

	User toDomain(UserDTO userDTO);


}
