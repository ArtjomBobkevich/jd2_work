package com.itacademy.web.util;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

@AllArgsConstructor
public class Filter {

    private static final Filter FILTER = new Filter();

    public static Filter getFILTER() {
        return FILTER;
    }

    @SneakyThrows
    public void addFilter(HttpServletRequest req) {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
    }

}