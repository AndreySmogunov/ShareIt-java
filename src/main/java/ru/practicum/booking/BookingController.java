package ru.practicum.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @GetMapping
    public List<Booking> getBookings(@RequestHeader("X-Sharer-User-Id") long userId) {
        return bookingService.getBookings(userId);
    }

    @PostMapping
    public Booking addNewBooking(@RequestHeader("X-Sharer-User-Id") long userId, @RequestBody Booking booking) {
        return bookingService.addNewBooking(userId, booking);
    }

    @PatchMapping("/{bookingId}")
    public Booking updateBooking(@RequestHeader("X-Sharer-User-Id") long userId, @PathVariable long bookingId, @RequestBody Booking booking) {
        return bookingService.updateBooking(userId, bookingId, booking);
    }

    @DeleteMapping("/{bookingId}")
    public void deleteBooking(@RequestHeader("X-Sharer-User-Id") long userId, @PathVariable long bookingId) {
        bookingService.deleteBooking(userId, bookingId);
    }
}