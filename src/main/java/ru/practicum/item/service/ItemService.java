package ru.practicum.item.service;

import ru.practicum.item.entity.Comment;
import ru.practicum.item.dto.ItemResponseDto;
import ru.practicum.item.dto.CommentRequestDto;
import java.util.List;

public interface ItemService {
    List<ItemResponseDto> getAllItemsByOwnerId(Long ownerId);
    ItemResponseDto getItemById(Long itemId, Long userId);
    Comment addComment(Long itemId, Long userId, CommentRequestDto dto);
}