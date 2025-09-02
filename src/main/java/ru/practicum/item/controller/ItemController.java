package ru.practicum.item.controller;

import ru.practicum.item.service.ItemService;
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
}