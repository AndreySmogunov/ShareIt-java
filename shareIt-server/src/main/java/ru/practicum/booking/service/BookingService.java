package ru.practicum.booking.service;

import org.springframework.stereotype.Service;
import ru.practicum.booking.entity.Booking;
import ru.practicum.booking.repository.BookingRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> getBookingsByUser(Long userId, String state) {
        switch (state) {
            case "ALL":
                return bookingRepository.findByUserId(userId);
            case "CURRENT":
                return bookingRepository.findByUserIdAndStartBeforeAndEndAfter(userId, LocalDateTime.now(), LocalDateTime.now());
            case "PAST":
                return bookingRepository.findByUserIdAndEndBefore(userId, LocalDateTime.now());
            case "FUTURE":
                return bookingRepository.findByUserIdAndStartAfter(userId, LocalDateTime.now());
            case "WAITING":
                return bookingRepository.findByUserIdAndStatus(userId, Booking.Status.WAITING);
            case "REJECTED":
                return bookingRepository.findByUserIdAndStatus(userId, Booking.Status.REJECTED);
            default:
                throw new IllegalArgumentException("Unknown state: " + state);
        }
    }

    public List<Booking> getBookingsByOwner(Long ownerId, String state) {
        switch (state) {
            case "ALL":
                return bookingRepository.findByItemIdOwnerId(ownerId);
            case "CURRENT":
                return bookingRepository.findByItemIdOwnerIdAndStartBeforeAndEndAfter(ownerId, LocalDateTime.now(), LocalDateTime.now());
            case "PAST":
                return bookingRepository.findByItemIdOwnerIdAndEndBefore(ownerId, LocalDateTime.now());
            case "FUTURE":
                return bookingRepository.findByItemIdOwnerIdAndStartAfter(ownerId, LocalDateTime.now());
            case "WAITING":
                return bookingRepository.findByItemIdOwnerIdAndStatus(ownerId, Booking.Status.WAITING);
            case "REJECTED":
                return bookingRepository.findByItemIdOwnerIdAndStatus(ownerId, Booking.Status.REJECTED);
            default:
                throw new IllegalArgumentException("Unknown state: " + state);
        }
    }

    public Booking addNewBooking(Long userId, Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking updateBookingStatus(Long userId, Long bookingId, Boolean approved) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking != null && booking.getItem().getOwner().getName().equals(userId)) {
            booking.setStatus(approved ? Booking.Status.APPROVED : Booking.Status.REJECTED);
            return bookingRepository.save(booking);
        }
        return null;
    }

    public Booking getBookingById(Long userId, Long bookingId) {
        return bookingRepository.findById(bookingId).orElse(null);
    }
}