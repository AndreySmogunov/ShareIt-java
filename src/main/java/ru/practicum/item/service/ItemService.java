package ru.practicum.item.service;

import ru.practicum.item.dto.ItemRequestDto;
import ru.practicum.item.dto.CommentRequestDto;
import ru.practicum.item.entity.Item;
import ru.practicum.item.entity.Comment;
import ru.practicum.item.entity.Request;
import java.util.List;

public interface ItemService {
    Item createItem(Long userId, ItemRequestDto dto);
    List<Item> getItemsByOwnerId(Long userId);
    Item getItemById(Long itemId, Long userId);
    Comment addComment(Long itemId, Long userId, CommentRequestDto dto);
    Request createRequest(Long userId, ItemRequestDto dto);
    List<Request> getUserRequests(Long userId);
    List<Request> getAllRequests();
    Request getRequestById(Long requestId);
}