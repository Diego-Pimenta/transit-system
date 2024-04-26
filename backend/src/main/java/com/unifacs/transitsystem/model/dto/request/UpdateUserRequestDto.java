package com.unifacs.transitsystem.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;

public record UpdateUserRequestDto(
        String address,

        @JsonProperty("phone_number")
        String phoneNumber,

        @Email
        String email,

        String password
) {}
