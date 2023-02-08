package com.example.airconditionsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AirconditionSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirconditionSystemApplication.class, args);
    }

}
