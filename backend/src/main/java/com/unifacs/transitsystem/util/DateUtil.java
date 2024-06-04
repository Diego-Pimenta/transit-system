package com.unifacs.transitsystem.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateUtil {

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LocalDateTime formatDate(LocalDateTime date) {
        String formattedDate = date.format(dtf);
        return LocalDateTime.parse(formattedDate, dtf);
    }
}
