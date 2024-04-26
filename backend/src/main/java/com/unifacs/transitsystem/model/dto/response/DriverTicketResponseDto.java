package com.unifacs.transitsystem.model.dto.response;

import java.util.UUID;

public record DriverTicketResponseDto(
        UUID id,
        String userCpf,
        UUID ticketId,
        String vehiclePlate
) {}
