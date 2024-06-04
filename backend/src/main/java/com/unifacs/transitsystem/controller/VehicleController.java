package com.unifacs.transitsystem.controller;

import com.unifacs.transitsystem.model.dto.request.CreateVehicleRequestDto;
import com.unifacs.transitsystem.model.dto.request.UpdateVehicleRequestDto;
import com.unifacs.transitsystem.model.dto.response.VehicleResponseDto;
import com.unifacs.transitsystem.service.impl.VehicleServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vehicles")
@Secured("WORKER")
public class VehicleController {

    private final VehicleServiceImpl service;

    @PostMapping
    public ResponseEntity<VehicleResponseDto> createVehicle(
            @RequestBody @Valid CreateVehicleRequestDto createVehicleRequestDto
    ) {
        return ResponseEntity.status(CREATED).body(service.createVehicle(createVehicleRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponseDto> getVehicle(
            @PathVariable("id") UUID id
    ) {
        return ResponseEntity.ok(service.getVehicle(id));
    }

    @GetMapping
    public ResponseEntity<List<VehicleResponseDto>> getAllVehicles() {
        return ResponseEntity.ok(service.getAllVehicles());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponseDto> updateVehicle(
            @PathVariable("id") UUID id,
            @RequestBody @Valid UpdateVehicleRequestDto updateVehicleRequestDto
    ) {
        return ResponseEntity.ok(service.updateVehicle(id, updateVehicleRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(
            @PathVariable("id") UUID id
    ) {
        service.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
