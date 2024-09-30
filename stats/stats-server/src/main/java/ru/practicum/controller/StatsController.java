package ru.practicum.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.stats.dto.EndpointHitDto;
import ru.practicum.ewm.stats.dto.ViewStatsDto;

import java.util.List;

@RestController
public class StatsController {

    @PostMapping("/hit")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createRecord(@RequestBody EndpointHitDto endpointHitDto) {
    }

    @GetMapping("/stats")
    public List<ViewStatsDto> getStats (
            @RequestParam(name = "start") String start,
            @RequestParam(name = "end") String end,
            @RequestParam(name = "uris", required = false) List<String> uris,
            @RequestParam(name = "unique", required = false) boolean unique
                                        ) {
        return null;
    }
}
