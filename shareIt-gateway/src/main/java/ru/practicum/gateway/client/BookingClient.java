package ru.practicum.gateway.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import ru.practicum.gateway.dto.BookingRequest;

@Service
public class BookingClient extends BaseClient {
    private static final String BASE_URL = "http://localhost:9090/bookings";

    public BookingClient(RestTemplateBuilder builder) {
        super(builder);
    }

    public ResponseEntity<?> getBookings(long userId, String state) {
        return restTemplate.getForEntity(
                UriComponentsBuilder.fromHttpUrl(BASE_URL)
                        .queryParam("userId", userId)
                        .queryParam("state", state)
                        .toUriString(),
                Object.class
        );
    }

    public ResponseEntity<?> createBooking(long userId, BookingRequest request) {
        return restTemplate.postForEntity(
                UriComponentsBuilder.fromHttpUrl(BASE_URL)
                        .queryParam("userId", userId)
                        .toUriString(),
                request,
                Object.class
        );
    }
}