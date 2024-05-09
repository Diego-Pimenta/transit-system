package com.unifacs.transitsystem.service.mapper;

import com.unifacs.transitsystem.model.dto.request.CreateVehicleRequestDto;
import com.unifacs.transitsystem.model.dto.request.UpdateVehicleRequestDto;
import com.unifacs.transitsystem.model.dto.response.VehicleResponseDto;
import com.unifacs.transitsystem.model.entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Mapper(componentModel = "spring", imports = { StringUtils.class, Objects.class })
public interface VehicleMapper {

    Vehicle createVehicleRequestDtoToVehicle(CreateVehicleRequestDto createVehicleRequestDto);

    @Mappings({
            @Mapping(
                    target = "model",
                    expression = "java(StringUtils.hasText(updateVehicleRequestDto.model()) ? updateVehicleRequestDto.model() : vehicle.getModel())"
            ),
            @Mapping(
                    target = "color",
                    expression = "java(StringUtils.hasText(updateVehicleRequestDto.color()) ? updateVehicleRequestDto.color() : vehicle.getColor())"
            ),
            @Mapping(
                    target = "year",
                    expression = "java(!Objects.isNull(updateVehicleRequestDto.year()) ? updateVehicleRequestDto.year() : vehicle.getYear())"
            )
    })
    Vehicle updateVehicleRequestDtoToVehicle(Vehicle vehicle, UpdateVehicleRequestDto updateVehicleRequestDto);

    VehicleResponseDto vehicleToVehicleResponseDto(Vehicle vehicle);
}
