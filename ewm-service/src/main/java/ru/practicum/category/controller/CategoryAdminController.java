package ru.practicum.category.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.category.service.CategoryAdminService;
import ru.practicum.dto.category.CategoryDto;
import ru.practicum.dto.category.NewCategoryDto;

@RestController
@RequestMapping("/admin/categories")
@Valid
@RequiredArgsConstructor
public class CategoryAdminController {
    private final CategoryAdminService categoryAdminService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto createCategory(@RequestBody NewCategoryDto newCategoryDto) {
        return categoryAdminService.createCategory(newCategoryDto);
    }

    @PatchMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto updateCategory(@PathVariable Long categoryId,
                                      @RequestBody NewCategoryDto newCategoryDto) {
        return categoryAdminService.updateCategory(categoryId, newCategoryDto);
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long categoryId) {
        categoryAdminService.deleteCategory(categoryId);
    }
}
