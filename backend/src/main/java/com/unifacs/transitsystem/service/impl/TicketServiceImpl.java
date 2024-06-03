package com.unifacs.transitsystem.service.impl;

import com.unifacs.transitsystem.exception.ResourceNotFoundException;
import com.unifacs.transitsystem.model.dto.request.CreateTicketRequestDto;
import com.unifacs.transitsystem.model.dto.request.UpdateTicketRequestDto;
import com.unifacs.transitsystem.model.dto.response.TicketResponseDto;
import com.unifacs.transitsystem.repository.TicketRepository;
import com.unifacs.transitsystem.service.TicketService;
import com.unifacs.transitsystem.service.mapper.TicketMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository repository;

    private final TicketMapper mapper;

    @Override
    public TicketResponseDto createTicket(CreateTicketRequestDto createTicketRequestDto) {
        var ticket = mapper.createTicketRequestDtoToTicket(createTicketRequestDto);

        var createdTicket = repository.save(ticket);

        return mapper.ticketToTicketResponseDto(createdTicket);
    }

    @Override
    public TicketResponseDto getTicket(UUID ticketId) {
        var ticket = repository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));

        return mapper.ticketToTicketResponseDto(ticket);
    }

    @Override
    public List<TicketResponseDto> getAllTickets() {
        var tickets = repository.findAll();

        return tickets
                .stream()
                .map(mapper::ticketToTicketResponseDto)
                .toList();
    }

    @Override
    public TicketResponseDto updateTicket(UUID ticketId, UpdateTicketRequestDto updateTicketRequestDto) {
        var ticket = repository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));

        var updatedTicket = mapper.updateTicketRequestDtoToTicket(ticket, updateTicketRequestDto);

        updatedTicket = repository.save(updatedTicket);

        return mapper.ticketToTicketResponseDto(updatedTicket);
    }

    @Override
    public void deleteTicket(UUID ticketId) {
        repository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));
        repository.deleteById(ticketId);
    }
}
