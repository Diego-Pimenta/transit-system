package com.unifacs.transitsystem.service;

import com.unifacs.transitsystem.model.dto.response.AllDriverTicketsResponseDto;
import com.unifacs.transitsystem.model.dto.response.AllDriverVehiclesResponseDto;
import com.unifacs.transitsystem.model.dto.response.AllVehicleTicketsResponseDto;

import java.util.List;

public interface SearchResultService {

    List<AllDriverVehiclesResponseDto> getAllDriverVehiclesByUserCpf(String userCpf);
    List<AllDriverTicketsResponseDto> getAllDriverTicketsByUserCpf(String userCpf);
    List<AllVehicleTicketsResponseDto> getAllDriverTicketsByVehicle(String vehiclePlate);
}
