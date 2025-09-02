package ru.practicum.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<ru.practicum.booking.entity.Booking, Long> {
    List<ru.practicum.booking.entity.Booking> findByUserId(Long userId);
    List<ru.practicum.booking.entity.Booking> findByItemId(Long itemId);
    Optional<ru.practicum.booking.entity.Booking> findByIdAndUserId(Long id, Long userId);
}