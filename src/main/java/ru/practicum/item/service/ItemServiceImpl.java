package ru.practicum.item.service;

import ru.practicum.item.repository.ItemRepository;
import ru.practicum.booking.repository.BookingRepository;
import ru.practicum.item.repository.CommentRepository;
import ru.practicum.item.dto.ItemResponseDto;
import ru.practicum.item.dto.CommentRequestDto;
import ru.practicum.item.entity.Comment;
import ru.practicum.item.entity.Item;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final BookingRepository bookingRepository;
    private final CommentRepository commentRepository;

    public ItemServiceImpl(
            ItemRepository itemRepository,
            BookingRepository bookingRepository,
            CommentRepository commentRepository) {
        this.itemRepository = itemRepository;
        this.bookingRepository = bookingRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public List<ItemResponseDto> getAllItemsByOwnerId(Long ownerId) {
        List<Item> items = itemRepository.findByOwnerId(ownerId);
        return items.stream()
                .map(item -> toItemResponseDto(item))
                .collect(Collectors.toList());
    }

    @Override
    public ItemResponseDto getItemById(Long itemId, Long userId) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));
        ItemResponseDto dto = toItemResponseDto(item);
        dto.setComments(commentRepository.findByItemId(itemId));
        return dto;
    }

    @Override
    public Comment addComment(Long itemId, Long userId, CommentRequestDto dto) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));

        // Проверка: пользователь должен арендовать вещь
        boolean hasBooking = bookingRepository.existsByItemIdAndUserIdAndEndBefore(itemId, userId, LocalDateTime.now());
        if (!hasBooking) {
            throw new RuntimeException("You can only comment after renting the item");
        }

        Comment comment = new Comment();
        comment.setItemId(itemId);
        comment.setUserId(userId);
        comment.setText(dto.getText());
        comment.setCreated(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    private ItemResponseDto toItemResponseDto(Item item) {
        ItemResponseDto dto = new ItemResponseDto();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setDescription(item.getDescription());
        dto.setAvailable(item.isAvailable());
        return dto;
    }
}