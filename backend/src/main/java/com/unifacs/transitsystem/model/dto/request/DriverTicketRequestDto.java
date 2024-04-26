package com.unifacs.transitsystem.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record DriverTicketRequestDto(
        @NotBlank
        @JsonProperty("user_cpf")
        String userCpf,

        @NotBlank
        @JsonProperty("ticket_id")
        UUID ticketId,

        @NotBlank
        @JsonProperty("vehicle_plate")
        String vehiclePlate
) {}
