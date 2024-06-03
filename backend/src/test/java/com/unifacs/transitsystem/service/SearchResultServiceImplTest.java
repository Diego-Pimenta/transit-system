package com.unifacs.transitsystem.service;

import com.unifacs.transitsystem.exception.ResourceNotFoundException;
import com.unifacs.transitsystem.model.dto.response.AllVehicleTicketsResponse;
import com.unifacs.transitsystem.model.dto.response.SearchResultResponse;
import com.unifacs.transitsystem.model.dto.response.TicketResponseDto;
import com.unifacs.transitsystem.model.dto.response.VehicleResponseDto;
import com.unifacs.transitsystem.model.entity.DriverTicket;
import com.unifacs.transitsystem.model.entity.Ticket;
import com.unifacs.transitsystem.model.entity.Vehicle;
import com.unifacs.transitsystem.repository.DriverTicketRepository;
import com.unifacs.transitsystem.service.impl.SearchResultServiceImpl;
import com.unifacs.transitsystem.service.mapper.TicketMapper;
import com.unifacs.transitsystem.service.mapper.VehicleMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SearchResultServiceImplTest {

    @Mock
    private DriverTicketRepository driverTicketRepository;

    @Mock
    private TicketMapper ticketMapper;

    @Mock
    private VehicleMapper vehicleMapper;

    @InjectMocks
    private SearchResultServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllDriverTicketsByVehicle_validPlate_returnsDriverTickets() {
        String vehiclePlate = "MOU7919";
        UUID driverTicketId = UUID.randomUUID();

        Vehicle vehicle = new Vehicle();
        vehicle.setPlate(vehiclePlate);

        LocalDateTime emissionDate = LocalDateTime.now();

        DriverTicket driverTicket = new DriverTicket();
        driverTicket.setVehicle(vehicle);
        driverTicket.setId(driverTicketId);
        driverTicket.setEmissionDate(emissionDate);
        driverTicket.setTicket(new Ticket());

        List<DriverTicket> driverTickets = Collections.singletonList(driverTicket);

        VehicleResponseDto vehicleResponseDto = new VehicleResponseDto(
                UUID.randomUUID(),
                vehiclePlate,
                "Toyota Corolla",
                "Red",
                2019
        );

        TicketResponseDto ticketResponseDto = new TicketResponseDto(
                UUID.randomUUID(),
                "Speeding",
                "Exceeded speed limit by 20 mph",
                new BigDecimal(150.00)
        );

        LocalDateTime expirationDate = emissionDate.plusMonths(3).with(LocalTime.MIDNIGHT);

        SearchResultResponse searchResultResponse = new SearchResultResponse(
                emissionDate,
                expirationDate,
                ticketResponseDto
        );

        when(driverTicketRepository.findAll())
                .thenReturn(driverTickets);
        when(vehicleMapper.vehicleToVehicleResponseDto(vehicle))
                .thenReturn(vehicleResponseDto);
        when(ticketMapper.ticketToTicketResponseDto(any()))
                .thenReturn(ticketResponseDto);

        AllVehicleTicketsResponse allVehicleTicketsResponse = service.getAllDriverTicketsByVehicle(vehiclePlate);

        assertEquals(vehicleResponseDto, allVehicleTicketsResponse.vehicle());
        assertEquals(Map.of(driverTicketId, searchResultResponse), allVehicleTicketsResponse.driverTickets());

        verify(driverTicketRepository, times(1))
                .findAll();
        verify(vehicleMapper, times(1))
                .vehicleToVehicleResponseDto(vehicle);
        verify(ticketMapper, times(1))
                .ticketToTicketResponseDto(any());
    }

    @Test
    void getAllDriverTicketsByVehicle_invalidPlate_throwsException() {
        String vehiclePlate = "INVALID_PLATE";

        List<DriverTicket> driverTickets = Collections.emptyList();

        when(driverTicketRepository.findAll())
                .thenReturn(driverTickets);

        assertThrows(
                ResourceNotFoundException.class,
                () -> service.getAllDriverTicketsByVehicle(vehiclePlate)
        );

        verify(driverTicketRepository, times(1))
                .findAll();
        verify(vehicleMapper, never())
                .vehicleToVehicleResponseDto(any());
        verify(ticketMapper, never())
                .ticketToTicketResponseDto(any());
    }
}
