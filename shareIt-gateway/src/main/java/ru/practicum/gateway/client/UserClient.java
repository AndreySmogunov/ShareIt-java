package ru.practicum.gateway.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.practicum.gateway.dto.UserRequest;

@Service
public class UserClient extends BaseClient {
    private static final String BASE_URL = "http://localhost:9090/users";

    public UserClient(RestTemplateBuilder builder) {
        super(builder);
    }

    public ResponseEntity<?> getUserById(long userId) {
        return restTemplate.getForEntity(BASE_URL + "/" + userId, Object.class);
    }

    public ResponseEntity<?> getAllUsers() {
        return restTemplate.getForEntity(BASE_URL, Object.class);
    }

    public ResponseEntity<?> createUser(UserRequest request) {
        return restTemplate.postForEntity(BASE_URL, request, Object.class);
    }
}