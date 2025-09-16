package ru.practicum.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.item.entity.Item;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByOwnerId(Long ownerId);
    List<Item> findByItemIdAndAvailableTrue(Long itemId);
    List<Item> findByRequest_Id(Long requestId); // ← Новый метод
}