package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.dto.mapper.EndpointHitMapper;
import ru.practicum.dto.mapper.ViewStatsMapper;
import ru.practicum.ewm.stats.dto.EndpointHitDto;
import ru.practicum.ewm.stats.dto.ViewStatsDto;
import ru.practicum.model.EndpointHit;
import ru.practicum.repository.StatsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsServiceBase implements StatsService {
    private final StatsRepository statsRepository;
    private final EndpointHitMapper endpointHitMapper;
    private final ViewStatsMapper viewStatsMapper;

    @Override
    public EndpointHitDto createRecord(EndpointHitDto endpointHitDto) {
        EndpointHit endpointHit = endpointHitMapper.endpointHitDtoToEndpointHit(endpointHitDto);
        endpointHit = statsRepository.save(endpointHit);
        return endpointHitMapper.endpointHitToEndpointHitDto(endpointHit);
    }

    @Override
    public List<ViewStatsDto> getStats(String start, String end, List<String> uris, boolean unique) {
        return List.of();
    }
}
