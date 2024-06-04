package com.unifacs.transitsystem.service;

import com.unifacs.transitsystem.model.dto.request.CreateVehicleRequestDto;
import com.unifacs.transitsystem.model.dto.request.UpdateVehicleRequestDto;
import com.unifacs.transitsystem.model.dto.response.VehicleResponseDto;

import java.util.List;
import java.util.UUID;

public interface VehicleService {

    VehicleResponseDto createVehicle(CreateVehicleRequestDto createVehicleRequestDto);
    VehicleResponseDto getVehicle(UUID vehicleId);
    List<VehicleResponseDto> getAllVehicles();
    VehicleResponseDto updateVehicle(UUID vehicleId, UpdateVehicleRequestDto updateVehicleRequestDto);
    void deleteVehicle(UUID vehicleId);
}
