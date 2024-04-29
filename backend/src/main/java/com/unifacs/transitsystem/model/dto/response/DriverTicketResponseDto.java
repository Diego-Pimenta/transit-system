package com.unifacs.transitsystem.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record DriverTicketResponseDto(
        UUID id,
        @JsonProperty("user_cpf")
        String userCpf,
        @JsonProperty("ticket_id")
        UUID ticketId,
        @JsonProperty("vehicle_plate")
        String vehiclePlate
) {}
