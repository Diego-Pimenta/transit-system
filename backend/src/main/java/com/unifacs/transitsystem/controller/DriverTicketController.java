package com.unifacs.transitsystem.controller;

import com.unifacs.transitsystem.model.dto.request.DriverTicketRequestDto;
import com.unifacs.transitsystem.model.dto.response.DriverTicketResponseDto;
import com.unifacs.transitsystem.service.impl.DriverTicketServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/driver-tickets")
@Secured("WORKER")
public class DriverTicketController {

    private final DriverTicketServiceImpl service;

    @PostMapping
    public ResponseEntity<DriverTicketResponseDto> createDriverTicket(
            @RequestBody @Valid DriverTicketRequestDto driverTicketRequestDto
    ) {
        return ResponseEntity.status(CREATED).body(service.createDriverTicket(driverTicketRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriverTicket(
            @PathVariable("id") UUID id
    ) {
        service.deleteDriverTicket(id);
        return ResponseEntity.noContent().build();
    }
}
