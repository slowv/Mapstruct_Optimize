package com.example.unscape.controller.impl;

import com.example.unscape.controller.AccessController;
import com.example.unscape.service.AccessService;
import com.example.unscape.service.dto.AccessDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccessControllerImpl implements AccessController {

    private final AccessService accessService;

    @Override
    public ResponseEntity<AccessDTO> getAccess(Long id) {
        return ResponseEntity.ok(accessService.getAccessDto(id));
    }
}
