package com.unifacs.transitsystem.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateUserRequestDto(
        String address,

        @JsonProperty("phone_number")
        String phoneNumber,

        @Email(message = "Email must be valid")
        String email,

        @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
        String password
) {}
