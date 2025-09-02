package ru.practicum.item.service;

import ru.practicum.item.dto.ItemResponseDto;
import java.util.List;

public interface ItemService {
    List<ItemResponseDto> getAllItemsByOwnerId(Long ownerId);
}