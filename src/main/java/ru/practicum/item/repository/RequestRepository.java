package ru.practicum.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.item.entity.Request;
import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByUserId(Long userId);
    List<Request> findAllByOrderByCreatedDesc();
}