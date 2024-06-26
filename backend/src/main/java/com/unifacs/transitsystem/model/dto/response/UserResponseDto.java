package com.unifacs.transitsystem.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.unifacs.transitsystem.model.entity.Role;

import java.util.UUID;

public record UserResponseDto(
        UUID id,
        String cpf,
        String name,
        String address,
        @JsonProperty("phone_number")
        String phoneNumber,
        String email,
        String password,
        Role role
) {}
