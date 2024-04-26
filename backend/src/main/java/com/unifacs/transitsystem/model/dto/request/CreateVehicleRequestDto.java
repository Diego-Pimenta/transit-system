package com.unifacs.transitsystem.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateVehicleRequestDto(
        @NotBlank
        String plate,

        @NotBlank
        String model,

        @NotBlank
        String color,

        @NotNull
        @Min(0)
        int year
) {}
