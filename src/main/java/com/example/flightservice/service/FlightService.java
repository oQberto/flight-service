package com.example.flightservice.service;

import com.example.flightservice.dao.FlightDao;
import com.example.flightservice.dto.FlightDto;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class FlightService {
    private static final FlightService INSTANCE = new FlightService();
    private final FlightDao flightDao = FlightDao.getInstance();

    public List<FlightDto> findAll() {
        return flightDao.findAll().stream()
                .map(flight -> FlightDto.builder()
                        .id(flight.getId())
                        .description("%s - %s - %s"
                                .formatted(
                                        flight.getDepartureAirportCode(),
                                        flight.getArrivalAirportCode(),
                                        flight.getStatus()
                                )
                        ).build()).collect(toList());
    }

    public static FlightService getInstance() {
        return INSTANCE;
    }
}
