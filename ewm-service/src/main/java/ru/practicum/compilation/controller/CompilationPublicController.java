package ru.practicum.compilation.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.StatsClient;
import ru.practicum.compilation.service.CompilationPublicService;
import ru.practicum.dto.compilation.CompilationDto;
import ru.practicum.ewm.stats.dto.EndpointHitDto;

import java.util.List;

import static ru.practicum.config.EWMServiceAppConfig.APP_NAME;

@RestController
@RequestMapping("/compilations")
@RequiredArgsConstructor
@Validated
public class CompilationPublicController {
    private final CompilationPublicService compilationPublicService;
    private final StatsClient statsClient;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CompilationDto> getFilteredCompilations(
            @RequestParam(name = "pinned", required = false) Boolean pinned,
            @RequestParam(name = "from", required = false, defaultValue = "0") @PositiveOrZero Integer from,
            @RequestParam(name = "size", required = false, defaultValue = "10") @Positive Integer size,
            HttpServletRequest request) {
        statsClient.createRecord(new EndpointHitDto(APP_NAME, request.getRequestURI(), request.getRemoteAddr()));
        return compilationPublicService.getFilteredCompilations(pinned, from, size);
    }

    @GetMapping("/{compId}")
    public CompilationDto getCompilationById(@PathVariable @Positive Long compId,
                                             HttpServletRequest request) {
        statsClient.createRecord(new EndpointHitDto(APP_NAME, request.getRequestURI(), request.getRemoteAddr()));
        return compilationPublicService.getCompilationById(compId);
    }
}
