package ru.practicum.booking.service;

import ru.practicum.booking.repository.BookingRepository;
import ru.practicum.booking.entity.Booking;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Booking> getBookingsByUser(long userId, String state) {
        return filterBookings(userId, state);
    }

    @Override
    public List<Booking> getBookingsByOwner(long ownerId, String state) {
        return filterBookingsByItemOwnerId(ownerId, state);
    }

    @Override
    public Booking addNewBooking(long userId, Booking booking) {
        booking.setUserId(userId);
        booking.setStatus("WAITING");
        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBookingStatus(long userId, long bookingId, boolean approved) {
        return bookingRepository.findByIdAndUserId(bookingId, userId)
                .map(b -> {
                    b.setStatus(approved ? "APPROVED" : "REJECTED");
                    return bookingRepository.save(b);
                })
                .orElseThrow(() -> new NoSuchElementException("Booking not found"));
    }

    @Override
    public Booking getBookingById(long userId, long bookingId) {
        return bookingRepository.findByIdAndUserId(bookingId, userId)
                .orElseThrow(() -> new NoSuchElementException("Booking not found"));
    }

    private List<Booking> filterBookings(long userId, String state) {
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        return switch (state) {
            case "ALL" -> bookings;
            case "CURRENT" -> bookings.stream()
                    .filter(b -> b.getStart().isBefore(LocalDateTime.now()) && b.getEnd().isAfter(LocalDateTime.now()))
                    .toList();
            case "PAST" -> bookings.stream()
                    .filter(b -> b.getEnd().isBefore(LocalDateTime.now()))
                    .toList();
            case "FUTURE" -> bookings.stream()
                    .filter(b -> b.getStart().isAfter(LocalDateTime.now()))
                    .toList();
            case "WAITING" -> bookings.stream()
                    .filter(b -> "WAITING".equals(b.getStatus()))
                    .toList();
            case "REJECTED" -> bookings.stream()
                    .filter(b -> "REJECTED".equals(b.getStatus()))
                    .toList();
            default -> throw new IllegalArgumentException("Unknown state: " + state);
        };
    }

    private List<Booking> filterBookingsByItemOwnerId(long ownerId, String state) {
        List<Booking> bookings = bookingRepository.findByItemId(ownerId);
        return filterByState(bookings, state);
    }

    private List<Booking> filterByState(List<Booking> bookings, String state) {
        return switch (state) {
            case "ALL" -> bookings;
            case "CURRENT" -> bookings.stream()
                    .filter(b -> b.getStart().isBefore(LocalDateTime.now()) && b.getEnd().isAfter(LocalDateTime.now()))
                    .toList();
            case "PAST" -> bookings.stream()
                    .filter(b -> b.getEnd().isBefore(LocalDateTime.now()))
                    .toList();
            case "FUTURE" -> bookings.stream()
                    .filter(b -> b.getStart().isAfter(LocalDateTime.now()))
                    .toList();
            case "WAITING" -> bookings.stream()
                    .filter(b -> "WAITING".equals(b.getStatus()))
                    .toList();
            case "REJECTED" -> bookings.stream()
                    .filter(b -> "REJECTED".equals(b.getStatus()))
                    .toList();
            default -> throw new IllegalArgumentException("Unknown state: " + state);
        };
    }
}