package ru.practicum.event.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.EventShortDto;
import ru.practicum.event.repository.EventRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventPublicServiceBase implements EventPublicService {
    private final EventRepository eventRepository;

    @Override
    public List<EventShortDto> getPublicisedEventsWithFilter(String text, List<Integer> categoriesIds, Boolean paid, String rangeStartString, String rangeEndString, Boolean onlyAvailable, String sort, Integer from, Integer size) {
        return List.of();
    }

    @Override
    public EventFullDto getEventById(Long eventId) {
        return null;
    }
}
