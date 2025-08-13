package ru.practicum.booking;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookingRepositoryImpl implements BookingRepository {
    private final List<Booking> bookings = new ArrayList<>();

    @Override
    public List<Booking> findByUserId(long userId) {
        return bookings.stream()
                .filter(booking -> booking.getUserId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public Booking findByUserIdAndBookingId(long userId, long bookingId) {
        return bookings.stream()
                .filter(booking -> booking.getUserId() == userId && booking.getId() == bookingId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Booking save(Booking booking) {
        if (booking.getId() == null) {
            booking.setId(getId());
        }
        bookings.removeIf(existingBooking -> existingBooking.getId().equals(booking.getId()));
        bookings.add(booking);
        return booking;
    }

    @Override
    public void deleteByUserIdAndBookingId(long userId, long bookingId) {
        bookings.removeIf(booking -> booking.getUserId() == userId && booking.getId() == bookingId);
    }

    private long getId() {
        long lastId = bookings.stream()
                .mapToLong(Booking::getId)
                .max()
                .orElse(0);
        return lastId + 1;
    }
}