package com.unifacs.transitsystem.service;

import com.unifacs.transitsystem.model.dto.request.DriverTicketRequestDto;
import com.unifacs.transitsystem.model.dto.response.DriverTicketResponseDto;

import java.util.UUID;

public interface DriverTicketService {

    DriverTicketResponseDto createDriverTicket(DriverTicketRequestDto createDriverTicketRequestDto);
    void deleteDriverTicket(UUID driverTicketId);
}
