package ru.practicum.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    public List<Item> getItems(long userId) {
        return itemRepository.findByUserId(userId);
    }

    @Override
    public Item addNewItem(long userId, Item item) {
        item.setUserId(userId);
        return itemRepository.save(item);
    }

    @Override
    public Item updateItem(long userId, long itemId, Item item) {
        Item existingItem = itemRepository.findByUserIdAndItemId(userId, itemId);
        if (existingItem != null) {
            existingItem.setName(item.getName());
            existingItem.setDescription(item.getDescription());
            existingItem.setAvailable(item.getAvailable());
            return itemRepository.save(existingItem);
        }
        return null;
    }

    @Override
    public Item getItemById(long userId, long itemId) {
        return itemRepository.findByUserIdAndItemId(userId, itemId);
    }

    @Override
    public void deleteItem(long userId, long itemId) {
        itemRepository.deleteByUserIdAndItemId(userId, itemId);
    }

    @Override
    public List<Item> searchItems(String text) {
        if (text == null || text.isBlank()) {
            return List.of();
        }
        return itemRepository.findAll().stream()
                .filter(item -> item.getAvailable() && (item.getName().contains(text) || item.getDescription().contains(text)))
                .collect(Collectors.toList());
    }
}