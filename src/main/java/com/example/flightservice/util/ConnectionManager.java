package com.example.flightservice.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@UtilityClass
public class ConnectionManager {
    private static final String PASSWORD_KEY = "db.password";
    private static final String USERNAME_KEY = "db.username";
    private static final String URL_KEY = "db.url";
    private static final String POOL_SIZE = "db.pool.size";
    private static final Integer DEFAULT_CONNECTION_POOL_SIZE = 10;
    private static BlockingQueue<Connection> pool;
    private static List<Connection> sourceConnections;

    static {
        loadDriver();
        initConnectionPool();
    }

    @SneakyThrows
    public static Connection getConnection() {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closePool() {
        try {
            for (Connection connection : sourceConnections) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initConnectionPool() {
        String size = PropertiesUtil.getProperty(POOL_SIZE);
        int poolSize = size == null ? DEFAULT_CONNECTION_POOL_SIZE : Integer.parseInt(size);

        pool = new ArrayBlockingQueue<>(poolSize);
        sourceConnections = new ArrayList<>(poolSize);

        for (int i = 0; i < poolSize; i++) {
            Connection connection = open();
            Connection proxyConnection = (Connection) Proxy.newProxyInstance(
                    ConnectionManager.class.getClassLoader(),
                    new Class[]{Connection.class},
                    (proxy, method, args) ->
                            method.getName().equals("close")
                                    ? pool.add(connection)
                                    : method.invoke(connection, args)
            );
            pool.add(proxyConnection);
            sourceConnections.add(connection);
        }
    }

    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    private Connection open() {
        return DriverManager.getConnection(
                PropertiesUtil.getProperty(URL_KEY),
                PropertiesUtil.getProperty(USERNAME_KEY),
                PropertiesUtil.getProperty(PASSWORD_KEY)
        );
    }
}
