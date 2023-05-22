package com.example.flightservice.dao;

import com.example.flightservice.entity.Flight;
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
public class FlightDao implements Dao<Long, Flight> {
    private static final FlightDao INSTANCE = new FlightDao();
    private static final String FIND_ALL_SQL = """
            SELECT id,
                 flight_no,
                 departure_date,
                 departure_airport_code,
                 arrival_date,
                 arrival_airport_code,
                 aircraft_id,
                 status
            FROM flight
            """;

    @Override
    public List<Flight> findAll() {
        List<Flight> flights = new ArrayList<>();
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                flights.add(buildFlight(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flights;
    }

    @Override
    public Optional<Flight> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(Flight entity) {

    }

    @Override
    public void save(Flight entity) {

    }

    public static FlightDao getInstance() {
        return INSTANCE;
    }

    private Flight buildFlight(ResultSet resultSet) throws SQLException {
        return Flight.builder()
                .id(resultSet.getLong("id"))
                .flightNo(resultSet.getString("flight_no"))
                .departureDate(resultSet.getTimestamp("departure_date").toLocalDateTime())
                .departureAirportCode(resultSet.getString("departure_airport_code"))
                .arrivalDate(resultSet.getTimestamp("arrival_date").toLocalDateTime())
                .arrivalAirportCode(resultSet.getString("arrival_airport_code"))
                .aircraftId(resultSet.getLong("aircraft_id"))
                .status(resultSet.getString("status"))
                .build();
    }
}
