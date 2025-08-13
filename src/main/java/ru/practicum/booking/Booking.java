package ru.practicum.booking;

import lombok.Data;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Booking {
    Long id;
    Long itemId;
    Long userId;
    LocalDateTime start;
    LocalDateTime end;
    String status;
}