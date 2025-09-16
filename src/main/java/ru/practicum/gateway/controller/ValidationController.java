package ru.practicum.gateway.controller;

import ru.practicum.gateway.service.ValidationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gateway")
public class ValidationController {
    private final ValidationService validationService;

    public ValidationController(ValidationService validationService) {
        this.validationService = validationService;
    }

    @PostMapping("/validate")
    public ValidationResult validateRequest(@RequestBody ValidationRequest request) {
        boolean isValid = validationService.validate(request);
        return new ValidationResult(isValid);
    }
}