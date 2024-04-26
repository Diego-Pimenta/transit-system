package com.unifacs.transitsystem.repository;

import com.unifacs.transitsystem.model.entity.DriverTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DriverTicketRepository extends JpaRepository<DriverTicket, UUID> {
}
