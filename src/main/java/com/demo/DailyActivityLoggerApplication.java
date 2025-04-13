package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.demo.model")
@EnableJpaRepositories("com.demo.repository")
public class DailyActivityLoggerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DailyActivityLoggerApplication.class, args);
    }
}