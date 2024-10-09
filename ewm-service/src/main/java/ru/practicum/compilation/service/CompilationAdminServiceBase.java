package ru.practicum.compilation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.compilation.repository.CompilationRepository;
import ru.practicum.dto.compilation.CompilationDto;
import ru.practicum.dto.compilation.NewCompilationDto;
import ru.practicum.dto.compilation.UpdateCompilationRequest;

@Service
@RequiredArgsConstructor
public class CompilationAdminServiceBase implements CompilationAdminService {
    private final CompilationRepository compilationRepository;

    @Override
    public CompilationDto createCompilation(NewCompilationDto newCompilationDto) {
        return null;
    }

    @Override
    public CompilationDto updateCompilation(Long compId, UpdateCompilationRequest updateCompilationRequest) {
        return null;
    }

    @Override
    public void deleteCompilation(Long compId) {

    }
}
