package com.unifacs.transitsystem.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequestDto(
        @NotBlank
        String cpf,

        @NotBlank
        String name,

        @NotBlank
        String address,

        @NotBlank
        @JsonProperty("phone_number")
        String phoneNumber,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String password
) {}
