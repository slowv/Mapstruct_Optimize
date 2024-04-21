package com.example.unscape.service;

import com.example.unscape.service.dto.AccessDTO;
import org.springframework.lang.NonNull;

public interface AccessService {
    AccessDTO getAccessDto(@NonNull final Long id);
}
