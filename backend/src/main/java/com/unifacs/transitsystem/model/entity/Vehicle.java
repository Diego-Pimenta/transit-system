package com.unifacs.transitsystem.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "vehicle", indexes = {@Index(name = "idx_vehicle_plate", columnList = "plate")})
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String plate;
    private String model;
    private String color;

    private Integer year;

    @OneToMany(mappedBy = "vehicle", orphanRemoval = true)
    private List<DriverTicket> driverTickets = new ArrayList<>();
}
