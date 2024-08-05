package com.example.unscape.service;

import com.example.unscape.service.dto.UserDTO;
import org.springframework.lang.NonNull;

public interface UserService {

    UserDTO getUser(@NonNull final Long id);

    UserDTO create(@NonNull final UserDTO dto);
}
