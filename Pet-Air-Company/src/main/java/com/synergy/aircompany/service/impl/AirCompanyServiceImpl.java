package com.synergy.aircompany.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synergy.aircompany.dto.AirCompanyDto;
import com.synergy.aircompany.model.AirCompany;
import com.synergy.aircompany.repository.AirCompanyRepo;
import com.synergy.aircompany.service.AirCompanyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class AirCompanyServiceImpl implements AirCompanyService {

    private final AirCompanyRepo companyRepo;

    @Override
    @Transactional
    public AirCompany save(AirCompanyDto airCompanyDto) {
        AirCompany airCompany = AirCompany.builder()
            .name(airCompanyDto.getName())
            .companyType(airCompanyDto.getAirCompanyType())
            .foundedAt(LocalDateTime.now())
            .build();

        log.info("Saving air company {}", airCompany);
        return companyRepo.save(airCompany);
    }

    @Override
    @Transactional
    public AirCompany update(AirCompanyDto airCompanyDto, Long airCompanyId) {

        log.info("Updating air company id={}", airCompanyId);

        AirCompany airCompany = getById(airCompanyId);
        airCompany.setCompanyType(airCompanyDto.getAirCompanyType());
        airCompany.setName(airCompanyDto.getName());

        log.info("Updated air company {}", airCompany);
        return airCompany;
    }

    @Override
    public AirCompany getById(Long airCompanyId) {
        log.info("Get Air Company by id={}", airCompanyId);
        return companyRepo
            .findById(airCompanyId)
            .orElseThrow(() -> new IllegalArgumentException(String.format("Air Company not found by id = %d", airCompanyId)));
    }
}
