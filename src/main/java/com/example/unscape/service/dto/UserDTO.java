package com.example.unscape.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String fullName;
    private List<AccessDTO> access = new ArrayList<>();
    private List<RoleDTO> roles = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String uuid;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String createdDate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String lastModifiedDate;
}
