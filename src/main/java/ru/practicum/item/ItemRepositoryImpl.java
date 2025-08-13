package ru.practicum.item;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemRepositoryImpl implements ItemRepository {
    private final List<Item> items = new ArrayList<>();

    @Override
    public List<Item> findByUserId(long userId) {
        return items.stream()
                .filter(item -> item.getUserId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public Item save(Item item) {
        item.setId(getId());
        items.add(item);
        return item;
    }

    @Override
    public void deleteByUserIdAndItemId(long userId, long itemId) {
        items.removeIf(item -> item.getUserId() == userId && item.getId() == itemId);
    }

    private long getId() {
        long lastId = items.stream()
                .mapToLong(Item::getId)
                .max()
                .orElse(0);
        return lastId + 1;
    }
}