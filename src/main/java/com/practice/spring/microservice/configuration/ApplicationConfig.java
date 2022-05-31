package com.practice.spring.microservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "application.user")
public class ApplicationConfig {

    @Value("allowed-fields")
    private List<String> allowedFields;


    public List<String> getAllowedFields() {
        return allowedFields;
    }
}
