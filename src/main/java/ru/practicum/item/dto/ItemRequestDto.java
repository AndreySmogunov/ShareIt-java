package ru.practicum.item.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ItemRequestDto {
    private String name;
    private String description;
    private boolean available;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long requestId;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public Long getRequestId() { return requestId; }
    public void setRequestId(Long requestId) { this.requestId = requestId; }
}