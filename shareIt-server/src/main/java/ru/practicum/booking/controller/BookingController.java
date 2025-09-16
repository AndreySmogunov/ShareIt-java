package ru.practicum.booking.controller;

import org.springframework.web.bind.annotation.*;
import ru.practicum.booking.service.BookingService;
import ru.practicum.booking.entity.Booking;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<Booking> getBookings(
            @RequestParam Long userId,
            @RequestParam(defaultValue = "ALL") String state) {
        return bookingService.getBookingsByUser(userId, state);
    }

    @GetMapping("/owner")
    public List<Booking> getOwnerBookings(
            @RequestParam Long userId,
            @RequestParam(defaultValue = "ALL") String state) {
        return bookingService.getBookingsByOwner(userId, state);
    }

    @PostMapping
    public Booking createBooking(
            @RequestParam Long userId,
            @RequestBody Booking booking) {
        return bookingService.addNewBooking(userId, booking);
    }

    @PatchMapping("/{bookingId}")
    public Booking updateBooking(
            @PathVariable Long bookingId,
            @RequestParam Boolean approved,
            @RequestParam Long userId) {
        return bookingService.updateBookingStatus(userId, bookingId, approved);
    }

    @GetMapping("/{bookingId}")
    public Booking getBookingById(
            @PathVariable Long bookingId,
            @RequestParam Long userId) {
        return bookingService.getBookingById(userId, bookingId);
    }
}