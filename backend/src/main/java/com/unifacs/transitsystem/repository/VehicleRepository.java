package com.unifacs.transitsystem.repository;

import com.unifacs.transitsystem.model.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
}
