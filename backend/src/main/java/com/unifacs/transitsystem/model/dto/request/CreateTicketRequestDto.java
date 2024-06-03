package com.unifacs.transitsystem.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateTicketRequestDto(
        @NotBlank(message = "Category must not be blank")
        String category,

        @NotBlank(message = "Description must not be blank")
        String description,

        @NotNull(message = "Cost must not be null")
        @Min(value = 0, message = "Cost must be a positive number")
        BigDecimal cost
) {}
