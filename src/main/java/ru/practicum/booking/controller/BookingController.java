package ru.practicum.booking.controller;

import ru.practicum.booking.service.BookingService;
import ru.practicum.booking.entity.Booking;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<Booking> getBookings(@RequestHeader("X-Sharer-User-Id") long userId,
                                     @RequestParam(defaultValue = "ALL") String state) {
        return bookingService.getBookingsByUser(userId, state);
    }

    @GetMapping("/owner")
    public List<Booking> getOwnerBookings(@RequestHeader("X-Sharer-User-Id") long userId,
                                          @RequestParam(defaultValue = "ALL") String state) {
        return bookingService.getBookingsByOwner(userId, state);
    }

    @PostMapping
    public Booking createBooking(@RequestHeader("X-Sharer-User-Id") long userId,
                                 @RequestBody Booking booking) {
        return bookingService.addNewBooking(userId, booking);
    }

    @PatchMapping("/{bookingId}")
    public Booking updateBooking(@RequestHeader("X-Sharer-User-Id") long userId,
                                 @PathVariable long bookingId,
                                 @RequestParam boolean approved) {
        return bookingService.updateBookingStatus(userId, bookingId, approved);
    }

    @GetMapping("/{bookingId}")
    public Booking getBooking(@RequestHeader("X-Sharer-User-Id") long userId,
                              @PathVariable long bookingId) {
        return bookingService.getBookingById(userId, bookingId);
    }
}