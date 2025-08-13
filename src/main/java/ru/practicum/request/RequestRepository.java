package ru.practicum.request;

import java.util.List;

public interface RequestRepository {
    List<Request> findByUserId(long userId);
    Request findByUserIdAndRequestId(long userId, long requestId);
    Request save(Request request);
    void deleteByUserIdAndRequestId(long userId, long requestId);
}