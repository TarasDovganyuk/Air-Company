package com.synergy.aircompany.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class FlightDto {

    private Long airCompanyId;
    private Long airplaneId;
    private String destinationCountry;
    private Long distance;
    private Long estimatedFlightTime;
}
