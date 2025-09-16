package ru.practicum.gateway.controller;

import org.springframework.http.ResponseEntity;
import ru.practicum.gateway.client.ItemClient;
import ru.practicum.gateway.dto.ItemRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gateway/items")
public class GatewayItemController {
    private final ItemClient itemClient;

    public GatewayItemController(ItemClient itemClient) {
        this.itemClient = itemClient;
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<?> getItemById(@PathVariable long itemId) {
        return itemClient.getItemById(itemId);
    }

    @GetMapping
    public ResponseEntity<?> getAllItems(@RequestParam long userId) {
        return itemClient.getAllItems(userId);
    }

    @PostMapping
    public ResponseEntity<?> createItem(@RequestParam long userId, @RequestBody ItemRequest request) {
        return itemClient.createItem(userId, request);
    }
}