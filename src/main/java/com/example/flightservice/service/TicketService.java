package com.example.flightservice.service;

import com.example.flightservice.dao.TicketDao;
import com.example.flightservice.dto.TicketDto;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class TicketService {
    private static final TicketService INSTANCE = new TicketService();
    private final TicketDao ticketDao = TicketDao.getInstance();

    public List<TicketDto> findByFlightId(Long flightId) {
        return ticketDao.findByFlightId(flightId).stream()
                .map(ticket -> TicketDto.builder()
                        .id(ticket.getId())
                        .description("%s-%s-%s".formatted(
                                ticket.getPassengerName(),
                                ticket.getSeatNo(),
                                ticket.getCost()
                        ))
                        .build()
                ).collect(toList());
    }

    public static TicketService getInstance() {
        return INSTANCE;
    }
}
