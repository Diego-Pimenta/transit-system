package com.unifacs.transitsystem.service.impl;

import com.unifacs.transitsystem.exception.ResourceNotFoundException;
import com.unifacs.transitsystem.model.dto.request.CreateVehicleRequestDto;
import com.unifacs.transitsystem.model.dto.request.UpdateVehicleRequestDto;
import com.unifacs.transitsystem.model.dto.response.VehicleResponseDto;
import com.unifacs.transitsystem.repository.DriverTicketRepository;
import com.unifacs.transitsystem.repository.VehicleRepository;
import com.unifacs.transitsystem.service.VehicleService;
import com.unifacs.transitsystem.service.mapper.VehicleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository repository;

    private final VehicleMapper mapper;

    @Override
    public VehicleResponseDto createVehicle(CreateVehicleRequestDto createVehicleRequestDto) {
        var vehicle = mapper.createVehicleRequestDtoToVehicle(createVehicleRequestDto);

        var createdVehicle = repository.save(vehicle);

        return mapper.vehicleToVehicleResponseDto(createdVehicle);
    }

    @Override
    public VehicleResponseDto getVehicle(UUID vehicleId) {
        var vehicle = repository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));

        return mapper.vehicleToVehicleResponseDto(vehicle);
    }

    @Override
    public List<VehicleResponseDto> getAllVehicles() {
        var vehicles = repository.findAll();

        return vehicles
                .stream()
                .map(mapper::vehicleToVehicleResponseDto)
                .toList();
    }

    @Override
    public VehicleResponseDto updateVehicle(UUID vehicleId, UpdateVehicleRequestDto updateVehicleRequestDto) {
        var vehicle = repository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));

        var updatedVehicle = mapper.updateVehicleRequestDtoToVehicle(vehicle, updateVehicleRequestDto);

        updatedVehicle = repository.save(updatedVehicle);

        return mapper.vehicleToVehicleResponseDto(updatedVehicle);
    }

    @Override
    public void deleteVehicle(UUID vehicleId) {
        repository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));
        repository.deleteById(vehicleId);
    }
}
