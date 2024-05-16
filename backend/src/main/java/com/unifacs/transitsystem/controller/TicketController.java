package com.unifacs.transitsystem.controller;

import com.unifacs.transitsystem.model.dto.request.CreateTicketRequestDto;
import com.unifacs.transitsystem.model.dto.request.UpdateTicketRequestDto;
import com.unifacs.transitsystem.model.dto.response.TicketResponseDto;
import com.unifacs.transitsystem.service.impl.TicketServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tickets")
@Secured("WORKER")
public class TicketController {

    private final TicketServiceImpl service;

    @PostMapping
    public ResponseEntity<TicketResponseDto> createTicket(
            @RequestBody @Valid CreateTicketRequestDto createTicketRequestDto
    ) {
        return ResponseEntity.status(CREATED).body(service.createTicket(createTicketRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDto> getTicket(
            @PathVariable("id") UUID id
    ) {
        return ResponseEntity.ok(service.getTicket(id));
    }

    @GetMapping
    public ResponseEntity<List<TicketResponseDto>> getAllTickets() {
        return ResponseEntity.ok(service.getAllTickets());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketResponseDto> updateTicket(
            @PathVariable("id") UUID id,
            @RequestBody @Valid UpdateTicketRequestDto updateTicketRequestDto
    ) {
        return ResponseEntity.ok(service.updateTicket(id, updateTicketRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(
            @PathVariable("id") UUID id
    ) {
        service.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }
}
