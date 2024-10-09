package ru.practicum.event.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.UpdateEventAdminRequest;
import ru.practicum.event.repository.EventRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventAdminServiceBase implements EventAdminService {
    private final EventRepository eventRepository;

    @Override
    public List<EventFullDto> getAllEventsWithFilter(List<Integer> usersIds, List<String> states, List<Integer> categoriesIds, String rangeStartString, String rangeEndString, Integer from, Integer size) {
        return List.of();
    }

    @Override
    public EventFullDto updateEventByUser(Long userId, Long eventId, UpdateEventAdminRequest updateEventAdminRequest) {
        return null;
    }
}
