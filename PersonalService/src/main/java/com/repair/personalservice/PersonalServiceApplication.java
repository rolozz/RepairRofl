package com.repair.personalservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PersonalServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalServiceApplication.class, args);
    }

}
