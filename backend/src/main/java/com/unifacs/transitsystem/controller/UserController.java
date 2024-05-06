package com.unifacs.transitsystem.controller;

import com.unifacs.transitsystem.model.dto.response.UserResponseDto;
import com.unifacs.transitsystem.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Secured({ "USER", "WORKER" })
public class UserController {

    private final UserServiceImpl service;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }
}
