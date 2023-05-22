package com.example.flightservice.util;

import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

import static lombok.AccessLevel.PRIVATE;

@UtilityClass
@NoArgsConstructor(access = PRIVATE)
public class JspHelper {
    private static final String JSP_FORMAT = "/WEB-INF/jsp/%s";

    public static String get(String jspName) {
        return JSP_FORMAT.formatted(jspName);
    }
}
