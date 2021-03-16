package com.synergy.aircompany.service;

import com.synergy.aircompany.dto.AirplaneDto;
import com.synergy.aircompany.model.Airplane;

public interface AirplaneService {

    Airplane save(AirplaneDto airplaneDto);

    Airplane getById(Long airplaneId);

    Airplane changeCompany(Long airplaneId, Long airCompanyId);
}
