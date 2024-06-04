package com.unifacs.transitsystem.service.mapper;

import com.unifacs.transitsystem.model.dto.request.DriverTicketRequestDto;
import com.unifacs.transitsystem.model.dto.response.DriverTicketResponseDto;
import com.unifacs.transitsystem.model.entity.DriverTicket;
import com.unifacs.transitsystem.model.entity.Ticket;
import com.unifacs.transitsystem.model.entity.User;
import com.unifacs.transitsystem.model.entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DriverTicketMapper {

    @Mapping(target = "id", ignore = true)
    DriverTicket driverTicketRequestDtoToDriverTicket(DriverTicketRequestDto driverTicketRequestDto, User user, Ticket ticket, Vehicle vehicle);

    @Mappings({
            @Mapping(target = "userCpf", expression = "java(driverTicket.getUser().getCpf())"),
            @Mapping(target = "ticketId", expression = "java(driverTicket.getTicket().getId())"),
            @Mapping(target = "vehiclePlate", expression = "java(driverTicket.getVehicle().getPlate())")
    })
    DriverTicketResponseDto driverTicketToDriverTicketResponseDto(DriverTicket driverTicket);
}
