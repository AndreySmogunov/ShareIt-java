package ru.practicum.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.item.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {}