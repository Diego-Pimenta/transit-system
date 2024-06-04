package com.unifacs.transitsystem.model.dto.request;

import jakarta.validation.constraints.Min;

public record UpdateVehicleRequestDto(
        String model,
        String color,

        @Min(value = 0, message = "Year must be a positive number")
        int year
) {}
