package com.unifacs.transitsystem.controller;

import com.unifacs.transitsystem.model.dto.response.AllDriverTicketsResponse;
import com.unifacs.transitsystem.model.dto.response.AllVehicleTicketsResponse;
import com.unifacs.transitsystem.service.impl.SearchResultServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
@Secured({ "USER", "WORKER" })
public class SearchResultController {

    private final SearchResultServiceImpl service;

    @GetMapping("/user/{cpf}")
    public ResponseEntity<AllDriverTicketsResponse> getDriverTickets(
            @PathVariable("cpf") String userCpf
    ) {
        return ResponseEntity.ok(service.getAllDriverTicketsByUserCpf(userCpf));
    }

    @GetMapping("/vehicle/{plate}")
    public ResponseEntity<AllVehicleTicketsResponse> getVehicleTickets(
            @PathVariable("plate") String vehiclePlate
    ) {
        return ResponseEntity.ok(service.getAllDriverTicketsByVehicle(vehiclePlate));
    }
}
