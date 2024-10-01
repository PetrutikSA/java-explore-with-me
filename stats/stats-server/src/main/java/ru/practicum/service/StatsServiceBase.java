package ru.practicum.service;

import org.springframework.stereotype.Service;
import ru.practicum.ewm.stats.dto.EndpointHitDto;
import ru.practicum.ewm.stats.dto.ViewStatsDto;

import java.util.List;

@Service
public class StatsServiceBase implements StatsService {

    @Override
    public void createRecord(EndpointHitDto endpointHitDto) {

    }

    @Override
    public List<ViewStatsDto> getStats(String start, String end, List<String> uris, boolean unique) {
        return List.of();
    }
}
