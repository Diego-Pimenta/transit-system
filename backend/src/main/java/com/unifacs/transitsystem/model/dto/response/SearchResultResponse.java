package com.unifacs.transitsystem.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record SearchResultResponse(
        @JsonProperty("emission_date")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime emissionDate,
        @JsonProperty("expiration_date")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime expirationDate,
        TicketResponseDto ticket
){}
