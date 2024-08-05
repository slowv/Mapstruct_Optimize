package com.example.unscape.controller;


import com.example.unscape.service.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
public interface UserController {

    @GetMapping("/{id}")
    ResponseEntity<UserDTO> getUser(@NonNull @PathVariable("id") Long id);

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<UserDTO> create(@NonNull @Valid @ModelAttribute final UserDTO dto);
}
