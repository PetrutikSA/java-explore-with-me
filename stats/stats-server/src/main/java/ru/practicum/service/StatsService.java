package ru.practicum.service;

import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.ewm.stats.dto.EndpointHitDto;
import ru.practicum.ewm.stats.dto.ViewStatsDto;

import java.time.Instant;
import java.util.List;

public interface StatsService {

    EndpointHitDto createRecord(EndpointHitDto endpointHitDto);

    List<ViewStatsDto> getStats (String start, String end, List<String> uris, boolean unique);
}
