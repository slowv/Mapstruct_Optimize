package com.example.unscape.service.mapper;

import com.example.unscape.entity.auth.AccessEntity;
import com.example.unscape.service.dto.AccessDTO;
import org.mapstruct.Mapper;


/**
 * Mapper for the entity {@link AccessEntity} and its DTO {@link AccessDTO}.
 */
@Mapper(
        config = MapperConfig.class
)
public interface AccessMapper extends EntityMapper<AccessDTO, AccessEntity> {
}
