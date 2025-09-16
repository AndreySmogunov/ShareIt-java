package ru.practicum.gateway.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.practicum.gateway.dto.ItemRequest;

@Service
public class ItemClient extends BaseClient {
    private static final String BASE_URL = "http://localhost:9090/items";

    public ItemClient(RestTemplateBuilder builder) {
        super(builder);
    }

    public ResponseEntity<?> getItemById(long itemId) {
        return restTemplate.getForEntity(BASE_URL + "/" + itemId, Object.class);
    }

    public ResponseEntity<?> getAllItems(long userId) {
        return restTemplate.getForEntity(BASE_URL + "?userId=" + userId, Object.class);
    }

    public ResponseEntity<?> createItem(long userId, ItemRequest request) {
        return restTemplate.postForEntity(BASE_URL + "?userId=" + userId, request, Object.class);
    }
}