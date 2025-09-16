package ru.practicum.item.controller;

import ru.practicum.item.entity.Comment;
import ru.practicum.item.service.ItemService;
import ru.practicum.item.dto.ItemRequestDto;
import ru.practicum.item.dto.CommentRequestDto;
import ru.practicum.item.entity.Item;
import ru.practicum.item.entity.Request;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public Item createItem(
            @RequestHeader("X-Sharer-User-Id") Long userId,
            @RequestBody ItemRequestDto dto) {
        return itemService.createItem(userId, dto);
    }

    @GetMapping("/{itemId}")
    public Item getItemById(
            @PathVariable Long itemId,
            @RequestHeader("X-Sharer-User-Id") Long userId) {
        return itemService.getItemById(itemId, userId);
    }

    @GetMapping
    public List<Item> getItemsByOwnerId(@RequestHeader("X-Sharer-User-Id") Long userId) {
        return itemService.getItemsByOwnerId(userId);
    }

    @PostMapping("/{itemId}/comment")
    public Comment addComment(
            @PathVariable Long itemId,
            @RequestHeader("X-Sharer-User-Id") Long userId,
            @RequestBody CommentRequestDto dto) {
        return itemService.addComment(itemId, userId, dto);
    }

    @PostMapping("/requests")
    public Request createRequest(
            @RequestHeader("X-Sharer-User-Id") Long userId,
            @RequestBody ItemRequestDto dto) {
        return itemService.createRequest(userId, dto);
    }

    @GetMapping("/requests")
    public List<Request> getUserRequests(@RequestHeader("X-Sharer-User-Id") Long userId) {
        return itemService.getUserRequests(userId);
    }

    @GetMapping("/requests/all")
    public List<Request> getAllRequests() {
        return itemService.getAllRequests();
    }

    @GetMapping("/requests/{requestId}")
    public Request getRequestById(
            @PathVariable Long requestId,
            @RequestHeader("X-Sharer-User-Id") Long userId) {
        return itemService.getRequestById(requestId);
    }
}