package ru.practicum.request;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;

    @Override
    public List<Request> getRequests(long userId) {
        return requestRepository.findByUserId(userId);
    }

    @Override
    public Request addNewRequest(long userId, Request request) {
        request.setUserId(userId);
        return requestRepository.save(request);
    }

    @Override
    public Request updateRequest(long userId, long requestId, Request request) {
        Request existingRequest = requestRepository.findByUserIdAndRequestId(userId, requestId);
        if (existingRequest != null) {
            existingRequest.setDescription(request.getDescription());
            return requestRepository.save(existingRequest);
        }
        return null;
    }

    @Override
    public void deleteRequest(long userId, long requestId) {
        requestRepository.deleteByUserIdAndRequestId(userId, requestId);
    }
}