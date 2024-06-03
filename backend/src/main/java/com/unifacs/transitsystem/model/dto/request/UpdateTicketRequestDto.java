package com.unifacs.transitsystem.model.dto.request;

import jakarta.validation.constraints.Min;

import java.math.BigDecimal;

public record UpdateTicketRequestDto(
        String category,
        String description,

        @Min(value = 0, message = "Cost must be a positive number")
        BigDecimal cost
) {}
