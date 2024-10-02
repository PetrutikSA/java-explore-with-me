package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.model.EndpointHit;
import ru.practicum.model.ViewStats;

import java.time.Instant;
import java.util.List;

@Repository
public interface StatsRepository extends JpaRepository<EndpointHit, Long> {
    @Query(value = """
        SELECT new ru.practicum.model.ViewStats(eh.app, eh.uri, COUNT (eh.id))
        FROM EndpointHit eh
        WHERE eh.timestamp >= ?1 AND eh.timestamp <= ?2
        GROUP BY eh.app, eh.uri
    """
    )
    List<ViewStats> findViewStatsForAllUriAllIpsBetweenDates(Instant start, Instant end);

    @Query(value = """
        SELECT new ru.practicum.model.ViewStats(eh.app, eh.uri, COUNT (eh.id))
        FROM EndpointHit eh
        WHERE eh.uri IN ?3 AND eh.timestamp >= ?1 AND eh.timestamp <= ?2
        GROUP BY eh.app, eh.uri
    """
    )
    List<ViewStats> findViewStatsForSpecifiedUriAllIpsBetweenDates(Instant start, Instant end, List<String> uris);

    @Query(value = """
        SELECT DISTINCT new ru.practicum.model.ViewStats(eh.app, eh.uri, COUNT (eh.id))
        FROM EndpointHit eh
        WHERE eh.timestamp >= ?1 AND eh.timestamp <= ?2
        GROUP BY eh.app, eh.uri
    """
    )
    List<ViewStats> findViewStatsForAllUriDistinctIpsBetweenDates(Instant start, Instant end);

    @Query(value = """
        SELECT DISTINCT new ru.practicum.model.ViewStats(eh.app, eh.uri, COUNT (eh.id))
        FROM EndpointHit eh
        WHERE eh.uri IN ?3 AND eh.timestamp >= ?1 AND eh.timestamp <= ?2
        GROUP BY eh.app, eh.uri
    """
    )
    List<ViewStats> findViewStatsForSpecifiedUriDistinctIpsBetweenDates(Instant start, Instant end, List<String> uris);
}
