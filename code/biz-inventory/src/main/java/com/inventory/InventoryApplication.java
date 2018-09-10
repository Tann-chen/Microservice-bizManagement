package com.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class InventoryApplication {

    @Bean
    @LoadBalanced
    RestTemplate restTemplateBean() {
        return new RestTemplate();
    }

    public static void main(String[] args){
        SpringApplication.run(InventoryApplication.class, args);
    }
}

