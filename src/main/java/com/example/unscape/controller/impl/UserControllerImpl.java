package com.example.unscape.controller.impl;

import com.example.unscape.controller.UserController;
import com.example.unscape.service.UserService;
import com.example.unscape.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public ResponseEntity<UserDTO> getUser(@NonNull Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @Override
    public ResponseEntity<UserDTO> create(@NonNull UserDTO dto) {
        if (Objects.nonNull(dto.getId())) {
            throw new RuntimeException("");
        }

        final UserDTO user = this.userService.create(dto);
        return ResponseEntity
                .created(URI.create("/users/" + user.getId()))
                .body(user);
    }
}
