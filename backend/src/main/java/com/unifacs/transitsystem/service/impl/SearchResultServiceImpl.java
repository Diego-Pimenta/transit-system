package com.unifacs.transitsystem.service.impl;

import com.unifacs.transitsystem.exception.ResourceNotFoundException;
import com.unifacs.transitsystem.model.dto.response.AllDriverTicketsResponse;
import com.unifacs.transitsystem.model.dto.response.AllVehicleTicketsResponse;
import com.unifacs.transitsystem.model.dto.response.SearchResultResponse;
import com.unifacs.transitsystem.model.entity.DriverTicket;
import com.unifacs.transitsystem.repository.DriverTicketRepository;
import com.unifacs.transitsystem.service.SearchResultService;
import com.unifacs.transitsystem.service.mapper.TicketMapper;
import com.unifacs.transitsystem.service.mapper.UserMapper;
import com.unifacs.transitsystem.service.mapper.VehicleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
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

        var tickets = getTickets(driverTickets, predicate);

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

        var tickets = getTickets(driverTickets, predicate);

        return new AllVehicleTicketsResponse(vehicle, tickets);
    }

    private Map<UUID, SearchResultResponse> getTickets(List<DriverTicket> driverTickets, Predicate<DriverTicket> predicate) {
        return driverTickets
                .stream()
                .filter(predicate)
                .collect(Collectors.toMap(
                        DriverTicket::getId,
                        dt -> new SearchResultResponse(
                                dt.getEmissionDate(),
                                this.createExpirationDate(dt.getEmissionDate()),
                                ticketMapper.ticketToTicketResponseDto(dt.getTicket())
                        )
                ));
    }

    private LocalDateTime createExpirationDate(LocalDateTime date) {
        return date.plusMonths(3).with(LocalTime.MIDNIGHT);
    }
}
