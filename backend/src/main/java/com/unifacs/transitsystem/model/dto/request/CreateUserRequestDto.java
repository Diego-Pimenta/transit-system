package com.unifacs.transitsystem.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequestDto(
        @NotBlank(message = "CPF must not be blank")
        @Size(min = 11, max = 11, message = "CPF must have exactly 11 digits")
        String cpf,

        @NotBlank(message = "Name must not be blank")
        String name,

        @NotBlank(message = "Address must not be blank")
        String address,

        @NotBlank(message = "Phone number must not be blank")
        @JsonProperty("phone_number")
        String phoneNumber,

        @NotBlank(message = "Email must not be blank")
        @Email(message = "Email must be valid")
        String email,

        @NotBlank(message = "Password must not be blank")
        @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
        String password
) {}
