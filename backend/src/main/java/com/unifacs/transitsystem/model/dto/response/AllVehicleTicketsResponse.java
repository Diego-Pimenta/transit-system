package com.unifacs.transitsystem.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.UUID;

public record AllVehicleTicketsResponse(
        VehicleResponseDto vehicle,
        @JsonProperty("driver_tickets")
        Map<UUID, TicketResponseDto> driverTickets
) {}
