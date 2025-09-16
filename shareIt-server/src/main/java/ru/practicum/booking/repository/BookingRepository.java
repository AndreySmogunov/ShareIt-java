package ru.practicum.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.booking.entity.Booking;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Поиск бронирований по пользователю
    List<Booking> findByUserId(Long userId);

    List<Booking> findByUserIdAndStartBeforeAndEndAfter(Long userId, LocalDateTime start, LocalDateTime end);

    List<Booking> findByUserIdAndEndBefore(Long userId, LocalDateTime end);

    List<Booking> findByUserIdAndStartAfter(Long userId, LocalDateTime start);

    List<Booking> findByUserIdAndStatus(Long userId, Booking.Status status);

    // Поиск бронирований по владельцу вещи
    List<Booking> findByItemIdOwnerId(Long ownerId);

    List<Booking> findByItemIdOwnerIdAndStartBeforeAndEndAfter(Long ownerId, LocalDateTime start, LocalDateTime end);

    List<Booking> findByItemIdOwnerIdAndEndBefore(Long ownerId, LocalDateTime end);

    List<Booking> findByItemIdOwnerIdAndStartAfter(Long ownerId, LocalDateTime start);

    List<Booking> findByItemIdOwnerIdAndStatus(Long ownerId, Booking.Status status);
}