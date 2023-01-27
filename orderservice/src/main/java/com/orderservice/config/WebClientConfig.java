package com.orderservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    //The below is the bean for webclient which is used to communicate to other microservices
//    @Bean
//    public WebClient webClient(){
//        return WebClient.builder().build();
//    }

    //In the below we added the loadbalanced annotation so that even if 2 services with same name runs
    //the load is split btw them
    @Bean
    @LoadBalanced
    public WebClient.Builder webClient(){
        return WebClient.builder();
    }
}
