package com.synergy.aircompany.dto;

import com.synergy.aircompany.model.CompanyType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class AirCompanyDto {

    private String name;
    private CompanyType airCompanyType;
}
