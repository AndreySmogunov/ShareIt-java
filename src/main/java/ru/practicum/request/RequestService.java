package ru.practicum.request;

import java.util.List;

public interface RequestService {
    List<Request> getRequests(long userId);
    Request addNewRequest(long userId, Request request);
    Request updateRequest(long userId, long requestId, Request request);
    void deleteRequest(long userId, long requestId);
}