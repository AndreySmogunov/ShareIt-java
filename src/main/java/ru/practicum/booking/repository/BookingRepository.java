package ru.practicum.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.booking.entity.Booking;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByItemId(Long itemId);
    Optional<Booking> findByIdAndUserId(Long id, Long userId);
    boolean existsByItemIdAndUserIdAndEndBefore(Long itemId, Long userId, LocalDateTime end);

    List<Booking> findByUserId(long userId);
}