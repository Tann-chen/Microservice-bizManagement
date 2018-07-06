package com.authorize;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AuthorizeApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthorizeApplication.class, args);
    }
}
