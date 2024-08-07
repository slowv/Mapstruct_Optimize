package com.example.unscape.service.dto;

import com.example.unscape.validation.FileNotEmpty;
import com.example.unscape.validation.FileSize;
import com.example.unscape.validation.FileType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.apache.http.entity.ContentType;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO {
    private Long id;
    @NotBlank(message = "Không được để trống trường {fullName}")
    private String fullName;

    @FileSize(maxSize = 15000000, message = "File ảnh không được lớn quá 2MB")
    @FileNotEmpty(message = "Trường {avatar} không được để trống.")
    @FileType(contentType = {"image/png", "image/jpg", "image/jpeg"}, message = "Định dạng file phải là JPGE | PNG")
    private MultipartFile avatar;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<AccessDTO> access = new ArrayList<>();
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<RoleDTO> roles = new ArrayList<>();
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String uuid;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String createdDate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String lastModifiedDate;
}
