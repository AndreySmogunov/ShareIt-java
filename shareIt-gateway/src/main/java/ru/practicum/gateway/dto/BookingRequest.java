package ru.practicum.gateway.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

import java.time.LocalDateTime;

public class BookingRequest {
    @NotNull(message = "ID вещи не может быть null")
    private Long itemId;

    @NotNull(message = "Дата начала бронирования должна быть в будущем")
    private LocalDateTime start;

    @NotNull(message = "Дата окончания бронирования должна быть в будущем")
    private LocalDateTime end;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}