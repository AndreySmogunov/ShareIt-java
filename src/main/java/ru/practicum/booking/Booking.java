package ru.practicum.booking;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Booking {
    private Long id;
    private Long itemId;
    private Long userId;
    private LocalDateTime start;
    private LocalDateTime end;
    private String status;
}