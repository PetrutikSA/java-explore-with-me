package ru.practicum.dto.category;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class NewCategoryDto {
    @NotNull
    @Length(min = 1, max = 50)
    private String name;
}
