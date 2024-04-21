package com.example.unscape.service.impl;

import com.example.unscape.repository.UserRepository;
import com.example.unscape.service.UserService;
import com.example.unscape.service.dto.UserDTO;
import com.example.unscape.service.mapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO getUser(@NonNull final Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }
}
