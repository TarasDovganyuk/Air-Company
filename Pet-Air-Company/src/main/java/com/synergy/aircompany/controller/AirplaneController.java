package com.synergy.aircompany.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synergy.aircompany.dto.AirplaneDto;
import com.synergy.aircompany.model.Airplane;
import com.synergy.aircompany.service.AirplaneService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/airplane")
@RestController
public class AirplaneController {

    private final AirplaneService airplaneService;

    @ApiOperation(value = "Create airplane.",
        response = Airplane.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Airplane.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "A server failure has occurred.") })
    @PostMapping("/create")
    public Airplane save(@RequestBody AirplaneDto airplaneDto) {
        return airplaneService.save(airplaneDto);
    }

    @ApiOperation(value = "Change airplane company.",
        response = Airplane.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Airplane.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "A server failure has occurred.") })
    @PutMapping("/update/{id}")
    public Airplane changeCompany(@PathVariable Long id, @RequestParam Long airCompanyId) {
        return airplaneService.changeCompany(id, airCompanyId);
    }
}
