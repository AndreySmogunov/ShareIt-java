package ru.practicum.item;

import java.util.List;

public interface ItemRepository {
    List<Item> findByUserId(long userId);
    Item findByUserIdAndItemId(long userId, long itemId);
    Item save(Item item);
    void deleteByUserIdAndItemId(long userId, long itemId);
    List<Item> findAll();
}