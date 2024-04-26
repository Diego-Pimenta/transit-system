package com.unifacs.transitsystem.model.dto.request;

import jakarta.validation.constraints.Min;

public record UpdateVehicleRequestDto(
        String model,
        String color,

        @Min(0)
        int year
) {}
