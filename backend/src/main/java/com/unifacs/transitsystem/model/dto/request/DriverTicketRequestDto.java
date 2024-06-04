package com.unifacs.transitsystem.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record DriverTicketRequestDto(
        @NotNull(message = "Emission date must not be null")
        @JsonProperty("emission_date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime emissionDate,

        @NotBlank(message = "User CPF must not be blank")
        @JsonProperty("user_cpf")
        String userCpf,

        @NotNull(message = "Ticket id must not be null")
        @JsonProperty("ticket_id")
        UUID ticketId,

        @NotBlank(message = "Vehicle plate must not be blank")
        @JsonProperty("vehicle_plate")
        String vehiclePlate
) {}
