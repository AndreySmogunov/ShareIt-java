package ru.practicum.gateway.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseClient {
    protected final RestTemplate restTemplate;

    public BaseClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }
}