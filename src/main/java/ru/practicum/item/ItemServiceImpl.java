package ru.practicum.item;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

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
        Optional<Item> optionalItem = itemRepository.findByUserIdAndId(userId, itemId);
        if (optionalItem.isPresent()) {
            Item existingItem = optionalItem.get();
            if (!existingItem.getUserId().equals(userId)) {
                throw new RuntimeException("Item does not belong to the user");
            }
            existingItem.setName(item.getName());
            existingItem.setDescription(item.getDescription());
            existingItem.setAvailable(item.isAvailable());
            return itemRepository.save(existingItem);
        }
        return null;
    }

    @Override
    public Item getItemById(long userId, long itemId) {
        return itemRepository.findByUserIdAndId(userId, itemId).orElse(null);
    }

    @Override
    public void deleteItem(long userId, long itemId) {
        itemRepository.deleteByUserIdAndId(userId, itemId);
    }

    @Override
    public List<Item> searchItems(String text) {
        if (text == null || text.isBlank()) {
            return List.of();
        }
        return itemRepository.findAll().stream()
                .filter(item -> item.isAvailable() &&
                        (item.getName().contains(text) || item.getDescription().contains(text)))
                .collect(Collectors.toList());
    }
}