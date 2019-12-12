package com.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableDiscoveryClient
//@EnableFeignClients
public class ApplicationBoot {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationBoot.class, args);
    }
}
