package com.unifacs.transitsystem.service;

import com.unifacs.transitsystem.model.dto.request.CreateTicketRequestDto;
import com.unifacs.transitsystem.model.dto.request.UpdateTicketRequestDto;
import com.unifacs.transitsystem.model.dto.response.TicketResponseDto;

import java.util.List;
import java.util.UUID;

public interface TicketService {

    TicketResponseDto createTicket(CreateTicketRequestDto createTicketRequestDto);
    TicketResponseDto getTicket(UUID ticketId);
    List<TicketResponseDto> getAllTickets();
    TicketResponseDto updateTicket(UUID ticketId, UpdateTicketRequestDto updateTicketRequestDto);
    void deleteTicket(UUID ticketId);
}
