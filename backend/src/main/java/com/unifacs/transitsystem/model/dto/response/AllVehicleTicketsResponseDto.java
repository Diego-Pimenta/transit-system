package com.unifacs.transitsystem.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public record AllVehicleTicketsResponseDto(
        UUID id,
        @JsonProperty("vehicle")
        VehicleResponseDto vehiclePlate,
        @JsonProperty("tickets")
        List<TicketResponseDto> ticketsId
) {}
