package com.unifacs.transitsystem.service;

import com.unifacs.transitsystem.model.dto.response.AllDriverTicketsResponse;
import com.unifacs.transitsystem.model.dto.response.AllVehicleTicketsResponse;

public interface SearchResultService {

    AllDriverTicketsResponse getAllDriverTicketsByUserCpf(String userCpf);
    AllVehicleTicketsResponse getAllDriverTicketsByVehicle(String vehiclePlate);
}
