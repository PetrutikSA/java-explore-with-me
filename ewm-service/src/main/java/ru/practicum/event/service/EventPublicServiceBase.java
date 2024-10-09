package ru.practicum.event.service;

import org.springframework.stereotype.Service;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.EventShortDto;

import java.util.List;

@Service
public class EventPublicServiceBase implements EventPublicService {
    @Override
    public List<EventShortDto> getPublicisedEventsWithFilter(String text, List<Integer> categoriesIds, Boolean paid, String rangeStartString, String rangeEndString, Boolean onlyAvailable, String sort, Integer from, Integer size) {
        return List.of();
    }

    @Override
    public EventFullDto getEventById(Long eventId) {
        return null;
    }
}
