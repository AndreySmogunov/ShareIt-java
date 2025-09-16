package ru.practicum.gateway.controller;

public class ValidationResult {
    private boolean valid;

    public ValidationResult(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}