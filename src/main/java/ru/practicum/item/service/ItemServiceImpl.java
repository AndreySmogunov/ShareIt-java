package ru.practicum.item.service;

import ru.practicum.item.repository.ItemRepository;
import ru.practicum.booking.repository.BookingRepository;
import ru.practicum.item.dto.ItemResponseDto;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import ru.practicum.item.dto.ItemResponseDto;
import ru.practicum.booking.entity.Booking;


@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final BookingRepository bookingRepository;

    public ItemServiceImpl(ItemRepository itemRepository, BookingRepository bookingRepository) {
        this.itemRepository = itemRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<ItemResponseDto> getAllItemsByOwnerId(Long ownerId) {
        List<ru.practicum.item.entity.Item> items = itemRepository.findByOwnerId(ownerId);
        return items.stream()
                .map(item -> {
                    ItemResponseDto dto = new ItemResponseDto();
                    dto.setId(item.getId());
                    dto.setName(item.getName());
                    dto.setDescription(item.getDescription());
                    dto.setAvailable(item.isAvailable());

                    List<ru.practicum.booking.entity.Booking> bookings = bookingRepository.findByItemId(item.getId());
                    dto.setLastBooking(getLastBooking(bookings));
                    dto.setNextBooking(getNextBooking(bookings));

                    return dto;
                })
                .collect(Collectors.toList());
    }

    private ItemResponseDto.BookingInfo getLastBooking(List<ru.practicum.booking.entity.Booking> bookings) {
        return bookings.stream()
                .filter(b -> b.getEnd().isBefore(LocalDateTime.now()))
                .max(Comparator.comparing(ru.practicum.booking.entity.Booking::getEnd))
                .map(b -> new ItemResponseDto.BookingInfo(b.getStart(), b.getEnd()))
                .orElse(null);
    }

    private ItemResponseDto.BookingInfo getNextBooking(List<ru.practicum.booking.entity.Booking> bookings) {
        return bookings.stream()
                .filter(b -> b.getStart().isAfter(LocalDateTime.now()))
                .min(Comparator.comparing(ru.practicum.booking.entity.Booking::getStart))
                .map(b -> new ItemResponseDto.BookingInfo(b.getStart(), b.getEnd()))
                .orElse(null);
    }
}