package com.unifacs.transitsystem.service.mapper;

import com.unifacs.transitsystem.model.dto.request.DriverTicketRequestDto;
import com.unifacs.transitsystem.model.dto.response.DriverTicketResponseDto;
import com.unifacs.transitsystem.model.entity.DriverTicket;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { UserMapper.class, VehicleMapper.class, TicketMapper.class })
public interface DriverTicketMapper {

    DriverTicket driverTicketRequestDtoToDriverTicket(DriverTicketRequestDto driverTicketRequestDto);

    DriverTicketResponseDto driverTicketToDriverTicketResponseDto(DriverTicket driverTicket);
}
