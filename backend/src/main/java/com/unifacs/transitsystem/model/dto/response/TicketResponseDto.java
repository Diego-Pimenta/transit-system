package com.unifacs.transitsystem.model.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record TicketResponseDto(
        UUID id,
        String category,
        String description,
        BigDecimal cost
) {}
