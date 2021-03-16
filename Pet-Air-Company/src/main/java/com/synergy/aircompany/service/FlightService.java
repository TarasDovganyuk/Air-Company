package com.synergy.aircompany.service;

import java.util.List;

import com.synergy.aircompany.dto.FlightDto;
import com.synergy.aircompany.model.Flight;
import com.synergy.aircompany.model.FlightStatus;

public interface FlightService {

    Flight save(FlightDto flightDto);

    Flight changeStatus(Long flightId, FlightStatus flightStatus);

    List<Flight> getAllCompletedFlightsWithFlightTimeMoreThanEstimated();

    List<Flight> getAllActiveFlightsStartedMoreThanTwentyFourHoursAgo();

    List<Flight> getAllFlightsByAirCompanyAndStatus(String airCompanyName, FlightStatus flightStatus);

    Flight getById(Long id);
}
