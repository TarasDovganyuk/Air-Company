package com.synergy.aircompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synergy.aircompany.model.AirCompany;

public interface AirCompanyRepo extends JpaRepository<AirCompany, Long> {

}
