package com.unifacs.transitsystem.service;

import com.unifacs.transitsystem.model.dto.response.AllDriverTicketsResponse;
import com.unifacs.transitsystem.model.dto.response.AllVehicleTicketsResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface SearchResultService {

    AllDriverTicketsResponse getAllDriverTicketsByUserCpf(String userCpf);
    AllVehicleTicketsResponse getAllDriverTicketsByVehicle(String vehiclePlate);
}
