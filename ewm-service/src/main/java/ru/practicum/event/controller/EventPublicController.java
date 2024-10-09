package ru.practicum.event.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.EventShortDto;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventPublicController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EventShortDto> getPublicisedEventsWithFilter(
            @RequestParam(name = "text", required = false) String text,
            @RequestParam(name = "categories", required = false) List<Integer> categoriesIds,
            @RequestParam(name = "paid", required = false) Boolean paid,
            @RequestParam(name = "rangeStart", required = false) String rangeStartString,
            @RequestParam(name = "rangeEnd", required = false) String rangeEndString,
            @RequestParam(name = "onlyAvailable", required = false, defaultValue = "false") Boolean onlyAvailable,
            @RequestParam(name = "sort", required = false) String sort,
            @RequestParam(name = "from", required = false, defaultValue = "0") Integer from,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        return null;
    }

    @GetMapping("/eventId")
    @ResponseStatus(HttpStatus.OK)
    public EventFullDto getEventById(@PathVariable Long eventId) {
        return null;
    }
}
