package com.bookbridge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class  BookbridgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookbridgeApplication.class, args);
    }

}
