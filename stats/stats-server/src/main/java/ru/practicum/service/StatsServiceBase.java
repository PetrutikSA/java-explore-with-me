package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.dto.mapper.EndpointHitMapper;
import ru.practicum.dto.mapper.ViewStatsMapper;
import ru.practicum.ewm.stats.dto.EndpointHitDto;
import ru.practicum.ewm.stats.dto.ViewStatsDto;
import ru.practicum.model.EndpointHit;
import ru.practicum.model.ViewStats;
import ru.practicum.repository.StatsRepository;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static ru.practicum.ewm.stats.util.Constants.DATE_TIME_FORMATTER;

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
    public List<ViewStatsDto> getStats(String startString, String endString, List<String> uris, boolean unique) {
        Instant start = DATE_TIME_FORMATTER.parse(java.net.URLDecoder.decode(startString, StandardCharsets.UTF_8),
                Instant::from);
        Instant end = DATE_TIME_FORMATTER.parse(java.net.URLDecoder.decode(endString, StandardCharsets.UTF_8),
                Instant::from);
        List<ViewStats> viewStatsList;
        if (uris == null || uris.isEmpty()) {
            if (unique) {
                viewStatsList = statsRepository.findViewStatsForAllUriDistinctIpsBetweenDates(start, end);
            } else {
                viewStatsList = statsRepository.findViewStatsForAllUriAllIpsBetweenDates(start, end);
            }
        } else {
            if (unique) {
                viewStatsList = statsRepository.findViewStatsForSpecifiedUriDistinctIpsBetweenDates(start, end, uris);
            } else {
                viewStatsList = statsRepository.findViewStatsForSpecifiedUriAllIpsBetweenDates(start, end, uris);
            }
        }

        return viewStatsList.stream()
                .map(viewStatsMapper::viewStatsToViewStatsDto)
                .toList();
    }
}
