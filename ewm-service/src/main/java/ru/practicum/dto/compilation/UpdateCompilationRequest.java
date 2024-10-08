package ru.practicum.dto.compilation;

import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
public class UpdateCompilationRequest {
    @Length(min = 1, max = 50)
    private String title;
    @Positive
    private List<Integer> events;
    private Boolean pinned;
}