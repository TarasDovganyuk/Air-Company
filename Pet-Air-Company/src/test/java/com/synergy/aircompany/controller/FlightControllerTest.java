package com.synergy.aircompany.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.synergy.aircompany.dto.FlightDto;
import com.synergy.aircompany.model.FlightStatus;
import com.synergy.aircompany.service.FlightService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@SpringBootTest
@AutoConfigureMockMvc
class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FlightService flightService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void save() throws Exception {
        FlightDto flightDto = FlightDto.builder()
            .airCompanyId(1L)
            .airplaneId(1L)
            .distance(5000L)
            .estimatedFlightTime(8L)
            .destinationCountry("Portugal")
            .build();

        mockMvc.perform(post("/flight/create", flightDto)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(flightDto))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.airCompany.id").value(1L))
            .andExpect(jsonPath("$.airplane.id").value(1L))
            .andExpect(jsonPath("$.distance").value(5000L))
            .andExpect(jsonPath("$.estimatedFlightTime").value(8L))
            .andExpect(jsonPath("$.destinationCountry").value("Portugal"))
            .andExpect(jsonPath("$.id").value("11"))
            .andExpect(jsonPath("$.flightStatus").value("PENDING"));
    }

    @Test
    void changeStatus() throws Exception {

        assertEquals(FlightStatus.PENDING, flightService.getById(1L).getFlightStatus());

        mockMvc.perform(put("/flight/1/change-status")
        .param("Flight status", FlightStatus.ACTIVE.name()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.airCompany.id").value(1L))
            .andExpect(jsonPath("$.airplane.id").value(1L))
            .andExpect(jsonPath("$.distance").value(5000L))
            .andExpect(jsonPath("$.estimatedFlightTime").value(8L))
            .andExpect(jsonPath("$.destinationCountry").value("USA"))
            .andExpect(jsonPath("$.id").value("1"))
            .andExpect(jsonPath("$.flightStatus").value("ACTIVE"));
    }

    @Test
    void getAllCompleted() throws Exception {
        mockMvc.perform(get("/flight/flights-completed"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[*]").isArray())
            .andExpect(jsonPath("$[*]").value(Matchers.hasSize(3)))
            .andExpect(jsonPath("$[0].id").value(3))
            .andExpect(jsonPath("$[1].id").value(5))
            .andExpect(jsonPath("$[2].id").value(8));
    }

    @Test
    void getAllByStatusAndCompany() throws Exception {
        mockMvc.perform(get("/flight/flights-by-status").param("Flight status", FlightStatus.COMPLETED.name()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[*]").isArray())
            .andExpect(jsonPath("$[*]").value(Matchers.hasSize(1)))
            .andExpect(jsonPath("$[0].id").value(3));
    }

    @Test
    void getAllStartedMoreThan() throws Exception {
        mockMvc.perform(get("/flight/flights-log"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[*]").isArray())
            .andExpect(jsonPath("$[*]").value(Matchers.hasSize(2)))
            .andExpect(jsonPath("$[0].id").value(2))
            .andExpect(jsonPath("$[1].id").value(7));
    }
}