package com.unifacs.transitsystem.controller;

import com.unifacs.transitsystem.model.dto.request.CreateUserRequestDto;
import com.unifacs.transitsystem.model.dto.request.UpdateUserRequestDto;
import com.unifacs.transitsystem.model.dto.response.UserResponseDto;
import com.unifacs.transitsystem.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Secured({ "USER", "WORKER" })
public class UserController {

    private final UserServiceImpl service;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(
            @RequestBody @Valid CreateUserRequestDto createUserRequestDto
    ) {
        return ResponseEntity.status(CREATED).body(service.createUser(createUserRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(
            @PathVariable("id") UUID id
    ) {
        return ResponseEntity.ok(service.getUser(id));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable("id") UUID id,
            @RequestBody @Valid UpdateUserRequestDto updateUserRequestDto
    ) {
        return ResponseEntity.ok(service.updateUser(id, updateUserRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable("id") UUID id
    ) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
