package com.unifacs.transitsystem.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public record AllDriverTicketsResponseDto(
        UUID id,
        @JsonProperty("user")
        UserResponseDto userCpf,
        @JsonProperty("tickets")
        List<TicketResponseDto> ticketsId
) {}
