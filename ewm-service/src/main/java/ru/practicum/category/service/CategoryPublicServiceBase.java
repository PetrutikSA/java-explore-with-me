package ru.practicum.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.category.repository.CategoryRepository;
import ru.practicum.dto.category.CategoryDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryPublicServiceBase implements CategoryPublicService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        return null;
    }

    @Override
    public List<CategoryDto> getCategories(Integer from, Integer size) {
        return List.of();
    }
}
