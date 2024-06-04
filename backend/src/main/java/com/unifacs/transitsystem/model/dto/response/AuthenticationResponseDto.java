package com.unifacs.transitsystem.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthenticationResponseDto(
        UserResponseDto user,
        String token,
        @JsonProperty("expires_in")
        long expiresIn
) {}
