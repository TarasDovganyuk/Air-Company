package com.synergy.aircompany.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class AirplaneDto {

    private String name;
    private Long airCompanyId;
    private Long fuelCapacity;
}
