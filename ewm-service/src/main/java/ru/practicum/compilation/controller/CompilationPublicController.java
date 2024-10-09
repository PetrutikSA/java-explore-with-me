package ru.practicum.compilation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.dto.compilation.CompilationDto;

import java.util.List;

@RestController
@RequestMapping("/compilations")
public class CompilationPublicController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CompilationDto> getFilteredCompilations(
            @RequestParam(name = "pinned", required = false) Boolean pinned,
            @RequestParam(name = "from", required = false, defaultValue = "0") Integer from,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        return null;
    }

    @GetMapping("{compId}")
    public CompilationDto getCompilationById(@PathVariable Long compId) {
        return null;
    }
}
