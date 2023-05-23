package com.example.flightservice.util;

import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.sql.Connection;

@UtilityClass
public class ConnectionManager {
    private static final String DATABASE_DRIVER_CLASS = "db.driver.class";
    private static final String PASSWORD_KEY = "db.password";
    private static final String USERNAME_KEY = "db.username";
    private static final String URL_KEY = "db.url";
    private static final HikariDataSource SOURCE;

    static {
        SOURCE = new HikariDataSource();

        SOURCE.setDriverClassName(PropertiesUtil.getProperty(DATABASE_DRIVER_CLASS));

        SOURCE.setJdbcUrl(PropertiesUtil.getProperty(URL_KEY));
        SOURCE.setUsername(PropertiesUtil.getProperty(USERNAME_KEY));
        SOURCE.setPassword(PropertiesUtil.getProperty(PASSWORD_KEY));
    }

    @SneakyThrows
    public static Connection getConnection() {
        return SOURCE.getConnection();
    }
}
