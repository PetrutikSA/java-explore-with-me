package ru.practicum.compilation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.compilation.repository.CompilationRepository;
import ru.practicum.dto.compilation.CompilationDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompilationPublicServiceBase implements CompilationPublicService {
    private final CompilationRepository compilationRepository;

    @Override
    public List<CompilationDto> getFilteredCompilations(Boolean pinned, Integer from, Integer size) {
        return List.of();
    }

    @Override
    public CompilationDto getCompilationById(Long compId) {
        return null;
    }
}
