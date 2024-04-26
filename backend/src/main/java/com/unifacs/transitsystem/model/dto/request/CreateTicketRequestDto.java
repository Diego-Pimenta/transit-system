package com.unifacs.transitsystem.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateTicketRequestDto(
        @NotBlank
        String category,

        @NotBlank
        String description,

        @NotNull
        @Min(0)
        BigDecimal cost,

        @NotNull
        @JsonProperty("date_time")
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
        LocalDateTime dateTime
) {}
