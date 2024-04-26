package com.unifacs.transitsystem.model.dto.response;

import java.util.UUID;

public record VehicleResponseDto(
        UUID id,
        String plate,
        String model,
        String color,
        int year
) {}
