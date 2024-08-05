package com.example.unscape.service.impl;

import com.example.unscape.repository.AccessRepository;
import com.example.unscape.service.AccessService;
import com.example.unscape.service.dto.AccessDTO;
import com.example.unscape.service.mapper.AccessMapper;
import com.google.common.collect.Lists;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AccessServiceImpl implements AccessService {

    private final AccessRepository accessRepository;
    private final AccessMapper accessMapper;

    @Override
    public AccessDTO getAccessDto(@NonNull final Long id) {
        return accessRepository.findById(id)
                .map(accessMapper::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }
}
