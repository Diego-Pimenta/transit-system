package com.unifacs.transitsystem.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateVehicleRequestDto(
        @NotBlank(message = "Plate must not be blank")
        @Size(min = 7, max = 7, message = "Plate must have exactly 7 characters")
        String plate,

        @NotBlank(message = "Model must not be blank")
        String model,

        @NotBlank(message = "Color must not be blank")
        String color,

        @NotNull(message = "Year must not be null")
        @Min(value = 0, message = "Year must be a positive number")
        int year
) {}
