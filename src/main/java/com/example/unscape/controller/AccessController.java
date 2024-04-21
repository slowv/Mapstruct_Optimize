package com.example.unscape.controller;

import com.example.unscape.service.dto.AccessDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/access")
public interface AccessController {

    @GetMapping("/{id}")
    ResponseEntity<AccessDTO> getAccess(@PathVariable("id") Long id);
}
