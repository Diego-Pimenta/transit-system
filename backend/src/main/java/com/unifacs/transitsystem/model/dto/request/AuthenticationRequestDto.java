package com.unifacs.transitsystem.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequestDto(
        @NotBlank
        String cpf,

        @NotBlank
        String password
) {
}
