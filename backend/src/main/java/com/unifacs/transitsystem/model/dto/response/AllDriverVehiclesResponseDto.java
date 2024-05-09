package com.unifacs.transitsystem.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record AllDriverVehiclesResponseDto(
        @JsonProperty("user")
        UserResponseDto userCpf,
        @JsonProperty("vehicles")
        List<VehicleResponseDto> vehiclePlate
) {}
