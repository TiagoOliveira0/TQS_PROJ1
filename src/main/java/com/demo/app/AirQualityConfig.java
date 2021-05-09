package com.demo.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AirQualityConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
