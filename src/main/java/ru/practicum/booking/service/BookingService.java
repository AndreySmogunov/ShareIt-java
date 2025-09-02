package ru.practicum.booking.service;

import ru.practicum.booking.entity.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> getBookingsByUser(long userId, String state);
    List<Booking> getBookingsByOwner(long ownerId, String state);
    Booking addNewBooking(long userId, Booking booking);
    Booking updateBookingStatus(long userId, long bookingId, boolean approved);
    Booking getBookingById(long userId, long bookingId);
}