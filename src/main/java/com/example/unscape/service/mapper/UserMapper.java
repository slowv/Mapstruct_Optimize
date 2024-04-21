package com.example.unscape.service.mapper;

import com.example.unscape.entity.auth.UserEntity;
import com.example.unscape.service.dto.UserDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link UserEntity} and its DTO {@link UserDTO}.
 */
@Mapper(
        config = MapperConfig.class,
        uses = {RoleMapper.class, AccessMapper.class}
)
public interface UserMapper extends EntityMapper<UserDTO, UserEntity> {
}
