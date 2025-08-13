package ru.practicum.booking;

import java.util.List;

public interface BookingRepository {
    List<Booking> findByUserId(long userId);
    Booking findByUserIdAndBookingId(long userId, long bookingId);
    Booking save(Booking booking);
    void deleteByUserIdAndBookingId(long userId, long bookingId);
}