package com.synergy.aircompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synergy.aircompany.model.Flight;

public interface FlightRepo extends JpaRepository<Flight, Long> {
}
