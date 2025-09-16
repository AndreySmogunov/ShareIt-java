package ru.practicum.item.controller;

import org.springframework.web.bind.annotation.*;
import ru.practicum.item.service.ItemService;
import ru.practicum.item.entity.Item;
import ru.practicum.user.entity.User;

import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{itemId}")
    public User getItemById(@PathVariable Long itemId) {
        return (User) itemService.getItemById(itemId);
    }

    @GetMapping
    public Optional<Item> getAllItems(@RequestParam Long userId) {
        return itemService.getAllItemsByUser(userId);
    }

    @PostMapping
    public Item createItem(@RequestParam Long userId, @RequestBody Item item) {
        item.setOwner(itemService.getItemById(userId));
        return itemService.createItem(item);
    }
}