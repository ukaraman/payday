package com.payday.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class SendEmailServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SendEmailServiceApplication.class, args);
    }
}
