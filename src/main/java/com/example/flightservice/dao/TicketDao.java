package com.example.flightservice.dao;

import com.example.flightservice.entity.Ticket;
import com.example.flightservice.util.ConnectionManager;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class TicketDao implements Dao<Long, Ticket> {
    private static final TicketDao INSTANCE = new TicketDao();
    private static final String FIND_BY_FLIGHT_ID_SQL = """
            SELECT id,
                passenger_no,
                passenger_name,
                flight_id,
                seat_no,
                cost
            FROM ticket
            WHERE flight_id = ?
            """;

    public List<Ticket> findByFlightId(Long flightId) {
        List<Ticket> ticketsByFlightId = new ArrayList<>();

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_FLIGHT_ID_SQL)) {
            preparedStatement.setLong(1, flightId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ticketsByFlightId.add(ticketBuilder(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ticketsByFlightId;
    }

    @Override
    public List<Ticket> findAll() {
        return null;
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(Ticket entity) {

    }

    @Override
    public void save(Ticket entity) {

    }

    private Ticket ticketBuilder(ResultSet resultSet) throws SQLException {
        return Ticket.builder()
                .id(resultSet.getLong("id"))
                .passengerNo(resultSet.getString("passenger_no"))
                .passengerName(resultSet.getString("passenger_name"))
                .flightId(resultSet.getLong("flight_id"))
                .seatNo(resultSet.getString("seat_no"))
                .cost(resultSet.getDouble("cost"))
                .build();
    }

    public static TicketDao getInstance() {
        return INSTANCE;
    }
}
