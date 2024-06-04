package com.unifacs.transitsystem.repository;

import com.unifacs.transitsystem.model.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {
}
