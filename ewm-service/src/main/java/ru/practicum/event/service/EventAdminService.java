package ru.practicum.event.service;

import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.UpdateEventAdminRequest;

import java.util.List;

public interface EventAdminService {
    List<EventFullDto> getAllEventsWithFilter(List<Integer> usersIds,
                                              List<String> states,
                                              List<Integer> categoriesIds,
                                              String rangeStartString,
                                              String rangeEndString,
                                              Integer from,
                                              Integer size
    );

    EventFullDto updateEventByUser(Long userId, Long eventId, UpdateEventAdminRequest updateEventAdminRequest);
}
