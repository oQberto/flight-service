package com.example.flightservice.util;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;

import static lombok.AccessLevel.PRIVATE;

@UtilityClass
@NoArgsConstructor(access = PRIVATE)
public class ConnectionManager {
    private static final String PASSWORD_KEY = "db.password";
    private static final String USERNAME_KEY = "db.username";
    private static final String URL_KEY = "db.url";

    static {
        load();
    }

    @SneakyThrows
    public static Connection get() {
        return DriverManager.getConnection(
                URL_KEY,
                USERNAME_KEY,
                PASSWORD_KEY
        );
    }

    private static void load() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
