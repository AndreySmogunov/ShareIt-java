package ru.practicum.booking.controller;

import ru.practicum.booking.service.BookingService;
import ru.practicum.booking.entity.Booking;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private static final String USER_ID_HEADER = "X-Sharer-User-Id";

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<Booking> getBookings(
            @RequestHeader(USER_ID_HEADER) long userId,
            @RequestParam(defaultValue = "ALL") String state) {
        return bookingService.getBookingsByUser(userId, state);
    }

    @GetMapping("/owner")
    public List<Booking> getOwnerBookings(
            @RequestHeader(USER_ID_HEADER) long userId,
            @RequestParam(defaultValue = "ALL") String state) {
        return bookingService.getBookingsByOwner(userId, state);
    }

    @PostMapping
    public Booking createBooking(
            @RequestHeader(USER_ID_HEADER) long userId,
            @RequestBody Booking booking) {
        return bookingService.addNewBooking(userId, booking);
    }

    @PatchMapping("/{bookingId}")
    public Booking updateBooking(
            @RequestHeader(USER_ID_HEADER) long userId,
            @PathVariable long bookingId,
            @RequestParam boolean approved) {
        return bookingService.updateBookingStatus(userId, bookingId, approved);
    }

    @GetMapping("/{bookingId}")
    public Booking getBookingById(
            @RequestHeader(USER_ID_HEADER) long userId,
            @PathVariable long bookingId) {
        return bookingService.getBookingById(userId, bookingId);
    }
}