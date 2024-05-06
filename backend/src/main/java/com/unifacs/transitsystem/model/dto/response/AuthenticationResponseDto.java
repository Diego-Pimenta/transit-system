package com.unifacs.transitsystem.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.unifacs.transitsystem.model.entity.Role;

import java.util.UUID;

public record AuthenticationResponseDto(
        UUID id,
        String cpf,
        String name,
        String address,
        @JsonProperty("phone_number")
        String phoneNumber,
        String email,
        String password,
        Role role,
        String token,
        @JsonProperty("expires_in")
        long expiresIn
) {}
