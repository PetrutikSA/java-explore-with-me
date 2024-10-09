package ru.practicum.event.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.UpdateEventAdminRequest;

import java.util.List;

@RestController
@RequestMapping("/admin/events")
public class EventAdminController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EventFullDto> getAllEventsWithFilter(
            @RequestParam(name = "users", required = false) List<Integer> usersIds,
            @RequestParam(name = "states", required = false) List<String> states,
            @RequestParam(name = "categories", required = false) List<Integer> categoriesIds,
            @RequestParam(name = "rangeStart", required = false) String rangeStartString,
            @RequestParam(name = "rangeEnd", required = false) String rangeEndString,
            @RequestParam(name = "from", required = false, defaultValue = "0") Integer from,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size
    ) {
        return null;
    }

    @PatchMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public EventFullDto updateEventByUser(@PathVariable Long userId,
                                          @PathVariable Long eventId,
                                          @RequestBody UpdateEventAdminRequest updateEventAdminRequest) {
        return null;
    }
}
