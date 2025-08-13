package ru.practicum.request;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RequestRepositoryImpl implements RequestRepository {
    private final List<Request> requests = new ArrayList<>();

    @Override
    public List<Request> findByUserId(long userId) {
        return requests.stream()
                .filter(request -> request.getUserId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public Request findByUserIdAndRequestId(long userId, long requestId) {
        return requests.stream()
                .filter(request -> request.getUserId() == userId && request.getId() == requestId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Request save(Request request) {
        if (request.getId() == null) {
            request.setId(getId());
        }
        requests.removeIf(existingRequest -> existingRequest.getId().equals(request.getId()));
        requests.add(request);
        return request;
    }

    @Override
    public void deleteByUserIdAndRequestId(long userId, long requestId) {
        requests.removeIf(request -> request.getUserId() == userId && request.getId() == requestId);
    }

    private long getId() {
        long lastId = requests.stream()
                .mapToLong(Request::getId)
                .max()
                .orElse(0);
        return lastId + 1;
    }
}