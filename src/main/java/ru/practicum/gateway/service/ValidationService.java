package ru.practicum.gateway.service;

import ru.practicum.gateway.controller.ValidationRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ValidationService {

    public boolean validate(ValidationRequest request) {
        if (request.getUserId() == null || request.getUserId().isEmpty()) {
            return false;
        }

        if (request.getItemId() != null && !request.getItemId().matches("\\d+")) {
            return false;
        }

        if (request.getText() != null && request.getText().length() > 500) {
            return false;
        }

        if (request.getDate() != null && request.getDate().isBefore(LocalDateTime.now())) {
            return false;
        }

        return true;
    }
}