package ru.practicum.compilation.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.StatsClient;
import ru.practicum.compilation.service.CompilationAdminService;
import ru.practicum.dto.compilation.CompilationDto;
import ru.practicum.dto.compilation.NewCompilationDto;
import ru.practicum.dto.compilation.UpdateCompilationRequest;
import ru.practicum.ewm.stats.dto.EndpointHitDto;

import static ru.practicum.config.EWMServiceAppConfig.APP_NAME;

@RestController
@RequestMapping("/admin/compilations")
@RequiredArgsConstructor
@Validated
public class CompilationAdminController {
    private final CompilationAdminService compilationAdminService;
    private final StatsClient statsClient;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompilationDto createCompilation(@RequestBody @Valid NewCompilationDto newCompilationDto,
                                            HttpServletRequest request) {
        statsClient.createRecord(new EndpointHitDto(APP_NAME, request.getRequestURI(), request.getRemoteAddr()));
        return compilationAdminService.createCompilation(newCompilationDto);
    }

    @PatchMapping("/{compId}")
    @ResponseStatus(HttpStatus.OK)
    public CompilationDto updateCompilation(@PathVariable @Positive Long compId,
                                            @RequestBody @Valid UpdateCompilationRequest updateCompilationRequest,
                                            HttpServletRequest request) {
        statsClient.createRecord(new EndpointHitDto(APP_NAME, request.getRequestURI(), request.getRemoteAddr()));
        return compilationAdminService.updateCompilation(compId, updateCompilationRequest);
    }

    @DeleteMapping("/{compId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompilation(@PathVariable @Positive Long compId, HttpServletRequest request) {
        statsClient.createRecord(new EndpointHitDto(APP_NAME, request.getRequestURI(), request.getRemoteAddr()));
        compilationAdminService.deleteCompilation(compId);
    }
}
