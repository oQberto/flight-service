package com.example.flightservice.entity;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class Flight {
    Long id;
    String flightNo;
    LocalDate departureDate;
    String departureAirportCode;
    LocalDate arrivalDate;
    String arrivalAirportCode;
    Long aircraftId;
    String status;
}
