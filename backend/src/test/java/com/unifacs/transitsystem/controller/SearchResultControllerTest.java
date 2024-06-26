package com.unifacs.transitsystem.controller;

import com.unifacs.transitsystem.model.dto.response.AllVehicleTicketsResponse;
import com.unifacs.transitsystem.model.dto.response.SearchResultResponse;
import com.unifacs.transitsystem.model.dto.response.TicketResponseDto;
import com.unifacs.transitsystem.model.dto.response.VehicleResponseDto;
import com.unifacs.transitsystem.service.impl.SearchResultServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SearchResultControllerTest {

    @Mock
    private SearchResultServiceImpl service;

    @InjectMocks
    private SearchResultController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getVehicleTickets_validPlate_returnsOkResponse() {
        String vehiclePlate = "MOU7919";

        VehicleResponseDto vehicleResponseDto = new VehicleResponseDto(
                UUID.randomUUID(),
                vehiclePlate,
                "Toyota Corolla",
                "Red",
                2019
        );

        UUID driverTicketId = UUID.randomUUID();

        TicketResponseDto ticketResponseDto = new TicketResponseDto(
                UUID.randomUUID(),
                "Speeding",
                "Exceeded speed limit by 20 mph",
                new BigDecimal(150.00)
        );

        LocalDateTime emissionDate = LocalDateTime.now();
        LocalDateTime expirationDate = emissionDate.plusMonths(3).with(LocalTime.MIDNIGHT);

        SearchResultResponse searchResultResponse = new SearchResultResponse(
                emissionDate,
                expirationDate,
                ticketResponseDto
        );

        Map<UUID, SearchResultResponse> driverTickets = new HashMap<>();
        driverTickets.put(driverTicketId, searchResultResponse);

        AllVehicleTicketsResponse allVehicleTicketsResponse = new AllVehicleTicketsResponse(
                vehicleResponseDto,
                driverTickets
        );

        when(service.getAllDriverTicketsByVehicle(vehiclePlate))
                .thenReturn(allVehicleTicketsResponse);

        ResponseEntity<AllVehicleTicketsResponse> responseEntity = controller.getVehicleTickets(vehiclePlate);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(allVehicleTicketsResponse, responseEntity.getBody());

        verify(service, times(1))
                .getAllDriverTicketsByVehicle(vehiclePlate);
    }
}
