package com.unifacs.transitsystem.service.impl;

import com.unifacs.transitsystem.exception.ResourceNotFoundException;
import com.unifacs.transitsystem.model.dto.response.*;
import com.unifacs.transitsystem.model.entity.DriverTicket;
import com.unifacs.transitsystem.repository.DriverTicketRepository;
import com.unifacs.transitsystem.service.SearchResultService;
import com.unifacs.transitsystem.service.mapper.TicketMapper;
import com.unifacs.transitsystem.service.mapper.UserMapper;
import com.unifacs.transitsystem.service.mapper.VehicleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchResultServiceImpl implements SearchResultService {

    private final DriverTicketRepository driverTicketRepository;

    private final UserMapper userMapper;

    private final TicketMapper ticketMapper;

    private final VehicleMapper vehicleMapper;

    @Override
    public AllDriverTicketsResponse getAllDriverTicketsByUserCpf(String userCpf) {
        return getTicketsByUser(driverTicket -> driverTicket.getUser().getCpf().equals(userCpf));
    }

    @Override
    public AllVehicleTicketsResponse getAllDriverTicketsByVehicle(String vehiclePlate) {
        return getTicketsByVehicle(driverTicket -> driverTicket.getVehicle().getPlate().equals(vehiclePlate));
    }

    private AllDriverTicketsResponse getTicketsByUser(Predicate<DriverTicket> predicate) {
        var driverTickets = driverTicketRepository.findAll();

        var user = driverTickets
                .stream()
                .filter(predicate)
                .findFirst()
                .map(DriverTicket::getUser)
                .map(userMapper::userToUserResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("No driver tickets were found for this user"));

        var tickets = getTickets(predicate);

        return new AllDriverTicketsResponse(user, tickets);
    }

    private AllVehicleTicketsResponse getTicketsByVehicle(Predicate<DriverTicket> predicate) {
        var driverTickets = driverTicketRepository.findAll();

        var vehicle = driverTickets
                .stream()
                .filter(predicate)
                .findFirst()
                .map(DriverTicket::getVehicle)
                .map(vehicleMapper::vehicleToVehicleResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("No driver tickets were found for this vehicle"));

        var tickets = getTickets(predicate);

        return new AllVehicleTicketsResponse(vehicle, tickets);
    }

    private Map<UUID, TicketResponseDto> getTickets(Predicate<DriverTicket> predicate) {
        return driverTicketRepository.findAll()
                .stream()
                .filter(predicate)
                .collect(Collectors.toMap(DriverTicket::getId, t -> ticketMapper.ticketToTicketResponseDto(t.getTicket())));
    }
}
