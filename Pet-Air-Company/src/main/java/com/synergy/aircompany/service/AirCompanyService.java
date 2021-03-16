package com.synergy.aircompany.service;

import com.synergy.aircompany.dto.AirCompanyDto;
import com.synergy.aircompany.model.AirCompany;


public interface AirCompanyService {

    AirCompany save(AirCompanyDto airCompanyDto);

    AirCompany update(AirCompanyDto airCompanyDto, Long airCompanyId);

    AirCompany getById(Long airCompanyId);
}
