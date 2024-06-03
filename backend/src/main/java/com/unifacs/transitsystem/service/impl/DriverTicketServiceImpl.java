package com.unifacs.transitsystem.service.impl;

import com.unifacs.transitsystem.exception.ResourceNotFoundException;
import com.unifacs.transitsystem.model.dto.request.DriverTicketRequestDto;
import com.unifacs.transitsystem.model.dto.response.DriverTicketResponseDto;
import com.unifacs.transitsystem.model.entity.DriverTicket;
import com.unifacs.transitsystem.model.entity.Ticket;
import com.unifacs.transitsystem.model.entity.User;
import com.unifacs.transitsystem.model.entity.Vehicle;
import com.unifacs.transitsystem.repository.DriverTicketRepository;
import com.unifacs.transitsystem.repository.TicketRepository;
import com.unifacs.transitsystem.repository.UserRepository;
import com.unifacs.transitsystem.repository.VehicleRepository;
import com.unifacs.transitsystem.service.DriverTicketService;
import com.unifacs.transitsystem.service.mapper.DriverTicketMapper;
import com.unifacs.transitsystem.util.DateUtil;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DriverTicketServiceImpl implements DriverTicketService {

    private final DriverTicketRepository driverTicketRepository;

    private final UserRepository userRepository;

    private final TicketRepository ticketRepository;

    private final VehicleRepository vehicleRepository;

    private final DriverTicketMapper mapper;

    private final DateUtil dateUtil;

    @Override
    public DriverTicketResponseDto createDriverTicket(DriverTicketRequestDto createDriverTicketRequestDto) {
        var user = this.getUser(createDriverTicketRequestDto.userCpf());
        var ticket = this.getTicket(createDriverTicketRequestDto.ticketId());
        var vehicle = this.getVehicle(createDriverTicketRequestDto.vehiclePlate());

        var driverTicket = mapper.driverTicketRequestDtoToDriverTicket(createDriverTicketRequestDto, user, ticket, vehicle);

        formatDriverTicketDate(driverTicket);

        var createdDriverTicket = driverTicketRepository.save(driverTicket);

        return mapper.driverTicketToDriverTicketResponseDto(createdDriverTicket);
    }

    @Override
    public void deleteDriverTicket(UUID driverTicketId) {
        driverTicketRepository.findById(driverTicketId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver ticket not found"));
        driverTicketRepository.deleteById(driverTicketId);
    }

    private void formatDriverTicketDate(DriverTicket driverTicket) {
        driverTicket.setEmissionDate(dateUtil.formatDate(driverTicket.getEmissionDate()));
    }

    private User getUser(String userCpf) {
        return userRepository.findByCpf(userCpf)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private Ticket getTicket(UUID ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));
    }

    private Vehicle getVehicle(String vehiclePlate) {
        return vehicleRepository.findByPlate(vehiclePlate)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));
    }
}
