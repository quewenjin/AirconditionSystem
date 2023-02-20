package com.example.airconditionsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@MapperScan("com.example.airconditionsystem.mapper")
public class AirconditionSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirconditionSystemApplication.class, args);
    }

}
