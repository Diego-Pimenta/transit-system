package com.unifacs.transitsystem.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public record DriverTicketResponseDto(
        UUID id,
        @JsonProperty("emission_date")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime emissionDate,
        @JsonProperty("user_cpf")
        String userCpf,
        @JsonProperty("ticket_id")
        UUID ticketId,
        @JsonProperty("vehicle_plate")
        String vehiclePlate
) {}
