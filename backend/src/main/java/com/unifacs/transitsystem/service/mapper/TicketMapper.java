package com.unifacs.transitsystem.service.mapper;

import com.unifacs.transitsystem.model.dto.request.CreateTicketRequestDto;
import com.unifacs.transitsystem.model.dto.request.UpdateTicketRequestDto;
import com.unifacs.transitsystem.model.dto.response.TicketResponseDto;
import com.unifacs.transitsystem.model.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Mapper(componentModel = "spring", imports = { StringUtils.class, Objects.class })
public interface TicketMapper {

    Ticket createTicketRequestDtoToTicket(CreateTicketRequestDto createTicketRequestDto);

    @Mapping(
            target = "category",
            expression = "java(StringUtils.hasText(updateTicketRequestDto.category()) ? updateTicketRequestDto.category() : ticket.getCategory())"
    )
    @Mapping(
            target = "description",
            expression = "java(StringUtils.hasText(updateTicketRequestDto.description()) ? updateTicketRequestDto.description() : ticket.getDescription())"
    )
    @Mapping(
            target = "cost",
            expression = "java(!Objects.isNull(updateTicketRequestDto.cost()) ? updateTicketRequestDto.cost() : ticket.getCost())"
    )
    @Mapping(
            target = "dateTime",
            expression = "java(updateTicketRequestDto.dateTime() != null ? updateTicketRequestDto.dateTime() : ticket.getDateTime())"
    )
    Ticket updateTicketRequestDtoToTicket(Ticket ticket, UpdateTicketRequestDto updateTicketRequestDto);

    TicketResponseDto ticketToTicketResponseDto(Ticket ticket);
}
