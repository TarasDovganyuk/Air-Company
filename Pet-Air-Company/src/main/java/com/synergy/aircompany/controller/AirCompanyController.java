package com.synergy.aircompany.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synergy.aircompany.dto.AirCompanyDto;
import com.synergy.aircompany.model.AirCompany;
import com.synergy.aircompany.service.AirCompanyService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RequestMapping("/air-company")
@RestController
@RequiredArgsConstructor
public class AirCompanyController {

    private final AirCompanyService companyService;

    @ApiOperation(value = "Create air company.",
        response = AirCompany.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = AirCompany.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "A server failure has occurred.") })
    @PostMapping("/create")
    public AirCompany save(@RequestBody AirCompanyDto companyDto) {
        return companyService.save(companyDto);
    }

    @ApiOperation(value = "Update air company.",
        response = AirCompany.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = AirCompany.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "A server failure has occurred.") })
    @PutMapping("/update")
    public AirCompany update(@RequestBody AirCompanyDto companyDto, @RequestParam Long airCompanyId) {
        return companyService.update(companyDto, airCompanyId);
    }

    @ApiOperation(value = "Get air company by id.",
        response = AirCompany.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = AirCompany.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "A server failure has occurred.") })
    @GetMapping("/{id}")
    public AirCompany get(@PathVariable Long id) {
        return companyService.getById(id);
    }
}
