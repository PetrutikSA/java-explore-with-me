package ru.practicum.ewm.stats.dto;

import lombok.Data;

@Data
public class EndpointHitDto {
    private String app;
    private String uri;
    private String ip;
}