package com.synergy.aircompany.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synergy.aircompany.dto.FlightDto;
import com.synergy.aircompany.model.Flight;
import com.synergy.aircompany.model.FlightStatus;
import com.synergy.aircompany.service.FlightService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/flight")
@RestController
public class FlightController {

    private final FlightService flightService;

    @ApiOperation(value = "Create flight.",
        response = Flight.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Flight.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "A server failure has occurred.") })
    @PostMapping("/create")
    public Flight save(@RequestBody FlightDto flightDto) {
        return flightService.save(flightDto);
    }

    @ApiOperation(value = "Change flight status.",
        response = Flight.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Flight.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "A server failure has occurred.") })
    @PutMapping("/{id}/change-status")
    public Flight changeStatus(@PathVariable Long id, @RequestParam(name = "Flight status") FlightStatus flightStatus) {
        return flightService.changeStatus(id, flightStatus);
    }

    @ApiOperation(value = "Get all completed flights with flight time more than estimated.",
        response = Flight.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Flight.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "A server failure has occurred.") })
    @GetMapping("/flights-completed")
    public List<Flight> getAllCompleted() {
        return flightService.getAllCompletedFlightsWithFlightTimeMoreThanEstimated();
    }

    @ApiOperation(value = "Find all air company flights by status.",
        response = Flight.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Flight.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "A server failure has occurred.") })
    @GetMapping("/flights-by-status")
    public List<Flight> getAllByStatusAndCompany(@RequestParam(name = "Company name", defaultValue = "LUFTHANSA") String companyName,
        @RequestParam(name = "Flight status") FlightStatus flightStatus) {
        return flightService.getAllFlightsByAirCompanyAndStatus(companyName, flightStatus);
    }

    @ApiOperation(value = "Get all flights started more than 24 hours ago.",
        response = Flight.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Flight.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "A server failure has occurred.") })
    @GetMapping("/flights-log")
    public List<Flight> getAllStartedMoreThan() {
        return flightService.getAllActiveFlightsStartedMoreThanTwentyFourHoursAgo();
    }
}
