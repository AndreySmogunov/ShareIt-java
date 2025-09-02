package ru.practicum.item.dto;

import ru.practicum.item.entity.Comment;
import java.time.LocalDateTime;
import java.util.List;

public class ItemResponseDto {
    private Long id;
    private String name;
    private String description;
    private boolean available;
    private List<Comment> comments; // ← добавлено поле

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public List<Comment> getComments() { return comments; }
    public void setComments(List<Comment> comments) { this.comments = comments; } // ← добавлен
}

    // Вложенный статический класс BookingInfo
    class BookingInfo {
        private LocalDateTime start;
        private LocalDateTime end;

        public BookingInfo() {}


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
