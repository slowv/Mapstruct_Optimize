package com.example.unscape.service.dto;

import com.example.unscape.converter.UnescapeStringConverter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
public class AccessDTO {
    private Long id;
    @JsonSerialize(using = UnescapeStringConverter.class)
    private String publicKey;
    private String uuid;
    private String createdDate;
    private String lastModifiedDate;
}
