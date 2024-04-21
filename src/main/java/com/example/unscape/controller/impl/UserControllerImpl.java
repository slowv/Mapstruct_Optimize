package com.example.unscape.controller.impl;

import com.example.unscape.controller.UserController;
import com.example.unscape.service.UserService;
import com.example.unscape.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public ResponseEntity<UserDTO> getUser(@NonNull Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }
}
