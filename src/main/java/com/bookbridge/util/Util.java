package com.bookbridge.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Util {
    public static void logError(String message, Exception e) {
        log.error(message);
        log.error("Error information -> {} \n ", e.getMessage());
        e.printStackTrace();
    }
}
