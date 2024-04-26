package com.unifacs.transitsystem.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final DateTimeFormatter formatRequest = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter formatResponse = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public static LocalDateTime formatRequestDate(LocalDateTime incomingDate) {
        return LocalDateTime.parse(incomingDate.format(formatRequest));
    }

    public static LocalDateTime formatResponseDate(LocalDateTime outgoingDate) {
        return LocalDateTime.parse(outgoingDate.format(formatResponse));
    }
}
