package ru.practicum.request;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;

    @GetMapping
    public List<Request> getRequests(@RequestHeader("X-Sharer-User-Id") long userId) {
        return requestService.getRequests(userId);
    }

    @PostMapping
    public Request addNewRequest(@RequestHeader("X-Sharer-User-Id") long userId, @RequestBody Request request) {
        return requestService.addNewRequest(userId, request);
    }

    @PatchMapping("/{requestId}")
    public Request updateRequest(@RequestHeader("X-Sharer-User-Id") long userId, @PathVariable long requestId, @RequestBody Request request) {
        return requestService.updateRequest(userId, requestId, request);
    }

    @DeleteMapping("/{requestId}")
    public void deleteRequest(@RequestHeader("X-Sharer-User-Id") long userId, @PathVariable long requestId) {
        requestService.deleteRequest(userId, requestId);
    }
}