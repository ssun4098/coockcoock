package com.coockcoock.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class CoockcoockApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoockcoockApplication.class, args);
    }

}
