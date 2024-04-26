package com.unifacs.transitsystem.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UpdateTicketRequestDto(
        String category,
        String description,

        @Min(0)
        BigDecimal cost,

        @JsonProperty("date_time")
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
        LocalDateTime dateTime
) {}
