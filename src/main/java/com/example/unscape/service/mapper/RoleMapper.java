package com.example.unscape.service.mapper;

import com.example.unscape.entity.auth.RoleEntity;
import com.example.unscape.service.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        config = MapperConfig.class
)
public interface RoleMapper extends EntityMapper<RoleDTO, RoleEntity> {
    @Override
    @Mapping(target = "name", expression = "java(\"ROLE_\" + role.getName())")
    RoleDTO toDto(RoleEntity role);
}
