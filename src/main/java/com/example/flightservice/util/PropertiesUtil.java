package com.example.flightservice.util;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.InputStream;
import java.util.Properties;

import static lombok.AccessLevel.PRIVATE;

@UtilityClass
@NoArgsConstructor(access = PRIVATE)
public class PropertiesUtil {
    private static final Properties PROPERTIES = new Properties();

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

    @SneakyThrows
    private static void loadProperties() {
        try (InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
        }
    }
}
