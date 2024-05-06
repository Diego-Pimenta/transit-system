package com.unifacs.transitsystem.controller;

import com.unifacs.transitsystem.model.dto.request.CreateUserRequestDto;
import com.unifacs.transitsystem.model.dto.response.UserResponseDto;
import com.unifacs.transitsystem.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
//@Secured("USER")
public class UserController {

    private final UserServiceImpl service;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(
            @RequestBody @Valid CreateUserRequestDto createUserRequestDto
            ) {
        return ResponseEntity.status(201).body(service.createUser(createUserRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }
}
