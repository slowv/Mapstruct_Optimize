package com.example.unscape.service.mapper;

import java.util.UUID;

@org.mapstruct.MapperConfig(
        componentModel = "spring",
        imports = {UUID.class}
)
public interface MapperConfig {
}
