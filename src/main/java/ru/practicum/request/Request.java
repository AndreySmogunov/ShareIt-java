package ru.practicum.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Request {
    private Long id;
    private Long userId;
    private String description;
    private LocalDateTime created;
}