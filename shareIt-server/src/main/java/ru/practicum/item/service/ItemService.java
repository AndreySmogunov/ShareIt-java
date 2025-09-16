package ru.practicum.item.service;

import ru.practicum.item.entity.Item;
import ru.practicum.user.entity.User;
import ru.practicum.item.repository.ItemRepository;
import ru.practicum.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Optional<Item> getAllItemsByUser(Long userId) {
        return itemRepository.findById(userId);
    }

    public org.apache.catalina.User getItemById(Long itemId) {
        return (org.apache.catalina.User) itemRepository.findById(itemId).orElse(null).getOwner();
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }
}