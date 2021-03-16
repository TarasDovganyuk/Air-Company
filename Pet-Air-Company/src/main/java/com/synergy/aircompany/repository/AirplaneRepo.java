package com.synergy.aircompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synergy.aircompany.model.Airplane;

public interface AirplaneRepo extends JpaRepository<Airplane, Long> {
}
