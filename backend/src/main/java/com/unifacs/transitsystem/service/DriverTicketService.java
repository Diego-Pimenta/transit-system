package com.unifacs.transitsystem.service;

import com.unifacs.transitsystem.model.dto.request.DriverTicketRequestDto;
import com.unifacs.transitsystem.model.dto.response.DriverTicketResponseDto;

import java.util.List;
import java.util.UUID;

public interface DriverTicketService {

    DriverTicketResponseDto createDriverTicket(DriverTicketRequestDto createDriverTicketRequestDto);
    DriverTicketResponseDto getDriverTicket(UUID driverTicketId);
    List<DriverTicketResponseDto> getAllDriverTickets();
    void deleteTicketRegistry();
}
