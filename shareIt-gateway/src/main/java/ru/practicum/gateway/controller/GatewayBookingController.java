package ru.practicum.gateway.controller;

import org.springframework.http.ResponseEntity;
import ru.practicum.gateway.client.BookingClient;
import ru.practicum.gateway.dto.BookingRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gateway/bookings")
public class GatewayBookingController {
    private final BookingClient bookingClient;

    public GatewayBookingController(BookingClient bookingClient) {
        this.bookingClient = bookingClient;
    }

    @GetMapping
    public ResponseEntity<?> getBookings(@RequestParam long userId, @RequestParam(defaultValue = "ALL") String state) {
        return bookingClient.getBookings(userId, state);
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestParam long userId, @RequestBody BookingRequest request) {
        return bookingClient.createBooking(userId, request);
    }
}