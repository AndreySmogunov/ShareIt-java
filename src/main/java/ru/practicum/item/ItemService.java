package ru.practicum.item;

import java.util.List;

public interface ItemService {
    List<Item> getItems(long userId);
    Item addNewItem(long userId, Item item);
    Item updateItem(long userId, long itemId, Item item);
    Item getItemById(long userId, long itemId);
    void deleteItem(long userId, long itemId);
    List<Item> searchItems(String text);
}