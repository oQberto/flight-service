package com.example.flightservice.entity;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Ticket {
    Long id;
    String passengerNo;
    String passengerName;
    Long flightId;
    String seatNo;
    Double cost;
}
