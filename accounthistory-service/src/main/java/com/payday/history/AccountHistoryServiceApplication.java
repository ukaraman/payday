package com.payday.history;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableCircuitBreaker
@EnableEurekaClient
public class AccountHistoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountHistoryServiceApplication.class, args);
    }
}
