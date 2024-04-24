package com.mca.application.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateUtils {

    DateUtils() {
        super();
    }

    public static LocalDateTime getDateTime(String date) {
        LocalDateTime dateTime;
        if (date.equals("now")) {
            dateTime = LocalDateTime.now();
        } else {
            dateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
        }
        return dateTime;
    }
}