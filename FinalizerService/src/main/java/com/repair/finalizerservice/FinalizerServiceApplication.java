package com.repair.finalizerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FinalizerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalizerServiceApplication.class, args);
    }

}
