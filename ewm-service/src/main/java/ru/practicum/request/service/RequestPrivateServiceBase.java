package ru.practicum.request.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.dto.event.request.ParticipationRequestDto;
import ru.practicum.request.repository.RequestRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestPrivateServiceBase implements RequestPrivateService {
    private final RequestRepository repository;

    @Override
    public ParticipationRequestDto createParticipationRequest(Long userId, Long eventId) {
        return null;
    }

    @Override
    public List<ParticipationRequestDto> getAllOwnParticipationRequests(Long userId) {
        return List.of();
    }

    @Override
    public ParticipationRequestDto cancelOwnParticipationInEvent(Long userId, Long requestId) {
        return null;
    }
}
