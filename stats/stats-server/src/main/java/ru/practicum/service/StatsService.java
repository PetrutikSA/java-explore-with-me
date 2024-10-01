package ru.practicum.service;

import ru.practicum.ewm.stats.dto.EndpointHitDto;
import ru.practicum.ewm.stats.dto.ViewStatsDto;
import ru.practicum.model.ViewStatsRequest;

import java.util.List;

public interface StatsService {

    EndpointHitDto createRecord(EndpointHitDto endpointHitDto);

    List<ViewStatsDto> getStats (ViewStatsRequest viewStatsRequest);
}
