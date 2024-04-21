package com.example.unscape.service.mapper;

import org.mapstruct.*;

import java.util.List;
import java.util.UUID;

/**
 * Contract for a generic dto to entity mapper.
 *
 * @param <D> - DTO type parameter.
 * @param <E> - Entity type parameter.
 */
public interface EntityMapper<D, E> {

    @Mapping(target = "uuid", expression = "java(UUID.randomUUID().toString())")
    D toDto(E e);

    @Mapping(target = "uuid", ignore = true)
    E toEntity(D d);

    List<D> toDto(List<E> e);

    List<E> toEntity(List<D> d);

    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    void partialUpdate(@MappingTarget E entity, D dto);
}
