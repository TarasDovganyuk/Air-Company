package com.synergy.aircompany.service.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synergy.aircompany.dto.FlightDto;
import com.synergy.aircompany.model.Airplane;
import com.synergy.aircompany.model.Flight;
import com.synergy.aircompany.model.FlightStatus;
import com.synergy.aircompany.repository.FlightRepo;
import com.synergy.aircompany.service.AirCompanyService;
import com.synergy.aircompany.service.AirplaneService;
import com.synergy.aircompany.service.FlightService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class FlightServiceImpl implements FlightService {

    private final FlightRepo flightRepo;
    private final AirplaneService airplaneService;
    private final AirCompanyService airCompanyService;

    @Override
    @Transactional
    public Flight save(FlightDto flightDto) {
        Airplane airplane = airplaneService.getById(flightDto.getAirplaneId());

        if (!airplane.getAirCompany().getId().equals(flightDto.getAirCompanyId())) {
            throw new IllegalArgumentException(String.format(
                "Airplane id=%d not found in air company id=%d", flightDto.getAirplaneId(), flightDto.getAirCompanyId()));
        }

        Flight flight = Flight.builder()
            .airCompany(airCompanyService.getById(flightDto.getAirCompanyId()))
            .airplane(airplane)
            .estimatedFlightTime(flightDto.getEstimatedFlightTime())
            .destinationCountry(flightDto.getDestinationCountry())
            .distance(flightDto.getDistance())
            .flightStatus(FlightStatus.PENDING)
            .build();

        log.info("Saving flight {}", flight);

        return flightRepo.save(flight);
    }

    @Override
    @Transactional
    public Flight changeStatus(Long flightId, FlightStatus flightStatus) {
        Flight flight = getById(flightId);

        switch (flightStatus) {
        case COMPLETED: {
            if (!flight.getFlightStatus().equals(FlightStatus.ACTIVE)) {
                throw new IllegalStateException("Flight status must be ACTIVE to complete");
            }
            flight.setEndedAt(LocalDateTime.now());
            flight.setFlightStatus(flightStatus);
            Airplane airplane = flight.getAirplane();
            airplane.setNumberOfFlights(airplane.getNumberOfFlights() + 1);
            airplane.setFlightDistance(airplane.getFlightDistance() + flight.getDistance());
            break;
        }
        case ACTIVE: {
            if (!(flight.getFlightStatus().equals(FlightStatus.PENDING) || flight.getFlightStatus().equals(FlightStatus.DELAYED))) {
                throw new IllegalStateException("Flight must be in status PENDING or DELAYED to become ACTIVE");
            }
            flight.setStartedAt(LocalDateTime.now());
            flight.setFlightStatus(flightStatus);
            break;
        }
        case DELAYED: {
            if (!flight.getFlightStatus().equals(FlightStatus.PENDING)) {
                throw new IllegalStateException("Flight must be in status PENDING to become DELAYED");
            }
            flight.setDelayStartedAt(LocalDateTime.now());
            flight.setFlightStatus(flightStatus);
            break;
        }
        case PENDING: {
            if (!flight.getFlightStatus().equals(FlightStatus.COMPLETED)) {
                throw new IllegalStateException("Flight must be in status COMPLETED to become PENDING");
            }
            flight.setFlightStatus(flightStatus);
            flight.setStartedAt(null);
            flight.setEndedAt(null);
            flight.setCreatedAt(LocalDateTime.now());
            break;
        }
        }
        log.info("Flight id={} flight status changed to {}", flightId, flightStatus.name());
        return flight;
    }

    @Override
    public List<Flight> getAllCompletedFlightsWithFlightTimeMoreThanEstimated() {
        return flightRepo.findAll()
            .stream()
            .filter(flight -> flight.getFlightStatus().equals(FlightStatus.COMPLETED))
            .filter(flight -> ChronoUnit.MINUTES.between(flight.getStartedAt(), flight.getEndedAt()) > flight.getEstimatedFlightTime() * 60)
            .collect(Collectors.toList());
    }

    @Override
    public List<Flight> getAllActiveFlightsStartedMoreThanTwentyFourHoursAgo() {
        return flightRepo.findAll()
            .stream()
            .filter(flight -> flight.getFlightStatus().equals(FlightStatus.ACTIVE))
            .filter(flight -> ChronoUnit.HOURS.between(flight.getStartedAt(), LocalDateTime.now()) > 24)
            .collect(Collectors.toList());
    }

    @Override
    public List<Flight> getAllFlightsByAirCompanyAndStatus(String airCompanyName, FlightStatus flightStatus) {
        return flightRepo.findAll()
            .stream()
            .filter(flight -> flight.getAirCompany().getName().equals(airCompanyName))
            .filter(flight -> flight.getFlightStatus().equals(flightStatus))
            .collect(Collectors.toList());
    }

    @Override
    public Flight getById(Long id) {
        return flightRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(String.format("Flight not found by id=%d", id)));
    }
}