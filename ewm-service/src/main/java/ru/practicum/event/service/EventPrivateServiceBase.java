package ru.practicum.event.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.EventShortDto;
import ru.practicum.dto.event.NewEventDto;
import ru.practicum.dto.event.UpdateEventUserRequest;
import ru.practicum.dto.event.request.EventRequestStatusUpdateRequest;
import ru.practicum.dto.event.request.EventRequestStatusUpdateResult;
import ru.practicum.dto.event.request.ParticipationRequestDto;
import ru.practicum.event.repository.EventRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventPrivateServiceBase implements EventPrivateService {
    private final EventRepository eventRepository;

    @Override
    public EventFullDto createNewEvent(Long userId, NewEventDto newEventDto) {
        return null;
    }

    @Override
    public List<EventShortDto> getAllUserEvents(Long userId, Integer from, Integer size) {
        return List.of();
    }

    @Override
    public EventFullDto getEventById(Long userId, Long eventId) {
        return null;
    }

    @Override
    public EventFullDto updateEventByUser(Long userId, Long eventId, UpdateEventUserRequest updateEventUserRequest) {
        return null;
    }

    @Override
    public List<ParticipationRequestDto> getParticipationRequestsOnEvent(Long userId, Long eventId) {
        return List.of();
    }

    @Override
    public EventRequestStatusUpdateResult responseOnParticipationRequests(Long userId, Long eventId, EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest) {
        return null;
    }
}
