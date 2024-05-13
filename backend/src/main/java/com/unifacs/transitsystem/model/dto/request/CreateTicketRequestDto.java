package com.unifacs.transitsystem.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateTicketRequestDto(
        @NotBlank(message = "Category must not be blank")
        String category,

        @NotBlank(message = "Description must not be blank")
        String description,

        @NotNull(message = "Cost must not be null")
        @Min(value = 0, message = "Cost must be a positive number")
        BigDecimal cost,

        @NotNull(message = "Emission date must not be null")
        @JsonProperty("emission_date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
        LocalDateTime emissionDate
) {}
