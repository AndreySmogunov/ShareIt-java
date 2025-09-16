package ru.practicum.item.service;

import ru.practicum.item.repository.ItemRepository;
import ru.practicum.item.repository.CommentRepository;
import ru.practicum.item.repository.RequestRepository;
import ru.practicum.item.dto.ItemRequestDto;
import ru.practicum.item.dto.CommentRequestDto;
import ru.practicum.item.entity.Item;
import ru.practicum.item.entity.Comment;
import ru.practicum.item.entity.Request;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final CommentRepository commentRepository;
    private final RequestRepository requestRepository;

    public ItemServiceImpl(
            ItemRepository itemRepository,
            CommentRepository commentRepository,
            RequestRepository requestRepository) {
        this.itemRepository = itemRepository;
        this.commentRepository = commentRepository;
        this.requestRepository = requestRepository;
    }

    @Override
    public Item createItem(Long userId, ItemRequestDto dto) {
        Item item = new Item();
        item.setOwnerId(userId);
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setAvailable(dto.isAvailable());

        if (dto.getRequestId() != null) {
            Request request = requestRepository.findById(dto.getRequestId())
                    .orElseThrow(() -> new RuntimeException("Request not found"));
            item.setRequest(request);
        }

        return itemRepository.save(item);
    }

    @Override
    public List<Item> getItemsByOwnerId(Long userId) {
        return List.of();
    }

    @Override
    public Item getItemById(Long itemId, Long userId) {
        return null;
    }

    @Override
    public Comment addComment(Long itemId, Long userId, CommentRequestDto dto) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        Comment comment = new Comment();
        comment.setItemId(itemId);
        comment.setUserId(userId);
        comment.setText(dto.getText());
        comment.setCreated(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    @Override
    public Request createRequest(Long userId, ItemRequestDto dto) {
        Request request = new Request();
        request.setDescription(dto.getDescription());
        request.setCreated(LocalDateTime.now());
        return requestRepository.save(request);
    }

    @Override
    public Request getRequestById(Long requestId) {
        return requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
    }

    @Override
    public List<Request> getUserRequests(Long userId) {
        return requestRepository.findByUserId(userId);
    }

    @Override
    public List<Request> getAllRequests() {
        return requestRepository.findAllByOrderByCreatedDesc();
    }
}