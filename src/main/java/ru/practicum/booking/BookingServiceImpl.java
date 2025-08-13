package ru.practicum.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    @Override
    public List<Booking> getBookings(long userId) {
        return bookingRepository.findByUserId(userId);
    }

    @Override
    public Booking addNewBooking(long userId, Booking booking) {
        booking.setUserId(userId);
        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(long userId, long bookingId, Booking booking) {
        Booking existingBooking = bookingRepository.findByUserIdAndBookingId(userId, bookingId);
        if (existingBooking != null) {
            existingBooking.setStart(booking.getStart());
            existingBooking.setEnd(booking.getEnd());
            existingBooking.setStatus(booking.getStatus());
            return bookingRepository.save(existingBooking);
        }
        return null;
    }

    @Override
    public void deleteBooking(long userId, long bookingId) {
        bookingRepository.deleteByUserIdAndBookingId(userId, bookingId);
    }
}