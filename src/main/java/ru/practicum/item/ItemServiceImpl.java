package ru.practicum.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Item existingItem = itemRepository.findByIdAndUserId(itemId, userId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        existingItem.setName(item.getName());
        existingItem.setDescription(item.getDescription());
        existingItem.setAvailable(item.isAvailable());

        return itemRepository.save(existingItem);
    }

    @Override
    public Item getItemById(long userId, long itemId) {
        return itemRepository.findByIdAndUserId(itemId, userId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    @Override
    public void deleteItem(long userId, long itemId) {
        itemRepository.deleteByIdAndUserId(itemId, userId);
    }

    @Override
    public List<Item> searchItems(String text) {
        if (text == null || text.isBlank()) {
            return List.of();
        }
        return itemRepository.findAll().stream()
                .filter(i -> i.isAvailable() &&
                        (i.getName().contains(text) || i.getDescription().contains(text)))
                .collect(java.util.stream.Collectors.toList());
    }
}