package com.synergy.aircompany.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synergy.aircompany.dto.AirplaneDto;
import com.synergy.aircompany.model.Airplane;
import com.synergy.aircompany.repository.AirplaneRepo;
import com.synergy.aircompany.service.AirCompanyService;
import com.synergy.aircompany.service.AirplaneService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class AirplaneServiceImpl implements AirplaneService {

    private final AirplaneRepo airplaneRepo;
    private final AirCompanyService airCompanyService;

    @Override
    @Transactional
    public Airplane save(AirplaneDto airplaneDto) {
        Airplane airplane = Airplane.builder()
            .createdAt(LocalDateTime.now())
            .name(airplaneDto.getName())
            .factorySerialNumber(UUID.randomUUID())
            .fuelCapacity(airplaneDto.getFuelCapacity())
            .flightDistance(0L)
            .numberOfFlights(0L)
            .build();

        if (airplaneDto.getAirCompanyId() != 0) {
            airplane.setAirCompany(airCompanyService.getById(airplaneDto.getAirCompanyId()));
        }

        log.info("Saving airplane {}", airplane);
        return airplaneRepo.save(airplane);
    }

    @Override
    public Airplane getById(Long airplaneId) {
        log.info("Get airplane by id={}", airplaneId);
        return airplaneRepo
            .findById(airplaneId)
            .orElseThrow(() -> new IllegalArgumentException(String.format("Airplane not found by id = %d", airplaneId)));
    }

    @Override
    @Transactional
    public Airplane changeCompany(Long airplaneId, Long airCompanyId) {
        log.info("Updating airplane company for airplane id={}", airplaneId);
        Airplane airplane = getById(airplaneId);
        airplane.setAirCompany(airCompanyService.getById(airCompanyId));
        log.info("Updated airplane {}", airplane);
        return airplane;
    }
}
