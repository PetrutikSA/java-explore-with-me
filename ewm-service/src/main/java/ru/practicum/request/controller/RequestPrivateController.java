package ru.practicum.request.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.StatsClient;
import ru.practicum.dto.event.request.ParticipationRequestDto;
import ru.practicum.ewm.stats.dto.EndpointHitDto;
import ru.practicum.request.service.RequestPrivateService;

import java.util.List;

import static ru.practicum.config.EWMServiceAppConfig.APP_NAME;

@RestController
@RequestMapping("/users/{userId}/requests")
@RequiredArgsConstructor
@Validated
public class RequestPrivateController {
    private final RequestPrivateService requestPrivateService;
    private final StatsClient statsClient;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipationRequestDto createParticipationRequest(@PathVariable @Positive Long userId,
                                                              @RequestParam @Positive Long eventId,
                                                              HttpServletRequest request) {
        statsClient.createRecord(new EndpointHitDto(APP_NAME, request.getRequestURI(), request.getRemoteAddr()));
        return requestPrivateService.createParticipationRequest(userId, eventId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ParticipationRequestDto> getAllOwnParticipationRequests(@PathVariable @Positive Long userId,
                                                                        HttpServletRequest request) {
        statsClient.createRecord(new EndpointHitDto(APP_NAME, request.getRequestURI(), request.getRemoteAddr()));
        return requestPrivateService.getAllOwnParticipationRequests(userId);
    }

    @PatchMapping("/{requestId}/cancel")
    @ResponseStatus(HttpStatus.OK)
    public ParticipationRequestDto cancelOwnParticipationInEvent(@PathVariable @Positive Long userId,
                                                                 @PathVariable @Positive Long requestId,
                                                                 HttpServletRequest request) {
        statsClient.createRecord(new EndpointHitDto(APP_NAME, request.getRequestURI(), request.getRemoteAddr()));
        return requestPrivateService.cancelOwnParticipationInEvent(userId, requestId);
    }
}
