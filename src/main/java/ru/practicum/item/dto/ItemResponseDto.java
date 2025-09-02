package ru.practicum.item.dto;

import java.time.LocalDateTime;

public class ItemResponseDto {
    private Long id;
    private String name;
    private String description;
    private boolean available;
    private BookingInfo lastBooking;
    private BookingInfo nextBooking;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public BookingInfo getLastBooking() {
        return lastBooking;
    }

    public void setLastBooking(BookingInfo lastBooking) {
        this.lastBooking = lastBooking;
    }

    public BookingInfo getNextBooking() {
        return nextBooking;
    }

    public void setNextBooking(BookingInfo nextBooking) {
        this.nextBooking = nextBooking;
    }

    // Вложенный статический класс BookingInfo
    public static class BookingInfo {
        private LocalDateTime start;
        private LocalDateTime end;

        public BookingInfo() {}

        public BookingInfo(LocalDateTime start, LocalDateTime end) {
            this.start = start;
            this.end = end;
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
}