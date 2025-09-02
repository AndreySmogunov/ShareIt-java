package ru.practicum.item.controller;

import ru.practicum.item.entity.Comment;
import ru.practicum.item.service.ItemService;
import ru.practicum.item.dto.CommentRequestDto;
import ru.practicum.item.dto.ItemResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<ItemResponseDto> getItems(@RequestHeader("X-Sharer-User-Id") Long userId) {
        return itemService.getAllItemsByOwnerId(userId);
    }

    @GetMapping("/{itemId}")
    public ItemResponseDto getItem(
            @PathVariable Long itemId,
            @RequestHeader("X-Sharer-User-Id") Long userId) {
        return itemService.getItemById(itemId, userId);
    }

    @PostMapping("/{itemId}/comment")
    public Comment addComment(
            @PathVariable Long itemId,
            @RequestHeader("X-Sharer-User-Id") Long userId,
            @RequestBody CommentRequestDto dto) {
        return itemService.addComment(itemId, userId, dto);
    }
}