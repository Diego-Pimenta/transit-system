package com.unifacs.transitsystem.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TicketResponseDto(
        UUID id,
        String category,
        String description,
        BigDecimal cost,
        @JsonProperty("emission_date")
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
        LocalDateTime emissionDate
) {}
