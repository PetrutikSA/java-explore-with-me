package ru.practicum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.stats.dto.EndpointHitDto;
import ru.practicum.ewm.stats.dto.ViewStatsDto;
import ru.practicum.model.ViewStatsRequest;
import ru.practicum.service.StatsService;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StatsController {
    private final StatsService statsService;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            .withZone(ZoneId.systemDefault());

    @PostMapping("/hit")
    @ResponseStatus(code = HttpStatus.CREATED)
    public EndpointHitDto createRecord(@RequestBody EndpointHitDto endpointHitDto) {
        return statsService.createRecord(endpointHitDto);
    }

    @GetMapping("/stats")
    public List<ViewStatsDto> getStats(
            @RequestParam(name = "start") String start,
            @RequestParam(name = "end") String end,
            @RequestParam(name = "uris", required = false) List<String> uris,
            @RequestParam(name = "unique", required = false, defaultValue = "false") boolean unique
    ) {
        Instant startInstant = dateTimeFormatter.parse(java.net.URLDecoder.decode(start, StandardCharsets.UTF_8),
                Instant::from);
        Instant endInstant = dateTimeFormatter.parse(java.net.URLDecoder.decode(start, StandardCharsets.UTF_8),
                Instant::from);
        ViewStatsRequest viewStatsRequest = ViewStatsRequest.builder()
                .start(startInstant)
                .end(endInstant)
                .uris(uris)
                .unique(unique)
                .build();
        return statsService.getStats(viewStatsRequest);
    }
}
