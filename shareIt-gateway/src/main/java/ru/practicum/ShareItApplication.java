package ru.practicum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"ru.practicum.booking.entity", "ru.practicum.item.entity", "ru.practicum.user.entity"})
public class ShareItApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShareItApplication.class, args);
    }
}