package ru.practicum.booking;

import java.util.List;

public interface BookingService {
    List<Booking> getBookings(long userId);
    Booking addNewBooking(long userId, Booking booking);
    Booking updateBooking(long userId, long bookingId, Booking booking);
    void deleteBooking(long userId, long bookingId);
}