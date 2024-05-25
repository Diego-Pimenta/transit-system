package com.unifacs.transitsystem.controller;

import com.unifacs.transitsystem.model.dto.request.DriverTicketRequestDto;
import com.unifacs.transitsystem.model.dto.response.DriverTicketResponseDto;
import com.unifacs.transitsystem.service.impl.DriverTicketServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DriverTicketControllerTest {

    @Mock
    private DriverTicketServiceImpl service;

    @InjectMocks
    private DriverTicketController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createDriverTicket_validRequest_returnsCreatedResponse() {
        String userCpf = "12345678902";
        UUID ticketId = UUID.randomUUID();
        String vehiclePlate = "MOU7919";

        DriverTicketRequestDto requestDto = new DriverTicketRequestDto(
                userCpf,
                ticketId,
                vehiclePlate
        );

        DriverTicketResponseDto driverTicketResponseDto = new DriverTicketResponseDto(
                UUID.randomUUID(),
                userCpf,
                ticketId,
                vehiclePlate
        );

        when(service.createDriverTicket(requestDto))
                .thenReturn(driverTicketResponseDto);

        ResponseEntity<DriverTicketResponseDto> responseEntity = controller.createDriverTicket(requestDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(driverTicketResponseDto, responseEntity.getBody());

        verify(service, times(1))
                .createDriverTicket(requestDto);
    }
}
