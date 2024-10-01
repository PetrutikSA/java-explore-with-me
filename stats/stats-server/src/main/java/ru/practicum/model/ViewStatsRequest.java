package ru.practicum.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class ViewStatsRequest {
    private Instant start;
    private Instant end;
    private List<String> uris;
    private boolean unique;
}
