package ru.practicum.gateway.controller;

import org.springframework.http.ResponseEntity;
import ru.practicum.gateway.client.UserClient;
import ru.practicum.gateway.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gateway/users")
public class GatewayUserController {
    private final UserClient userClient;

    public GatewayUserController(UserClient userClient) {
        this.userClient = userClient;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable long userId) {
        return userClient.getUserById(userId);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return userClient.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequest request) {
        return userClient.createUser(request);
    }
}