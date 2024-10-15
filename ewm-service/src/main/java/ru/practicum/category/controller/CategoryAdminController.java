package ru.practicum.category.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
import ru.practicum.category.service.CategoryAdminService;
import ru.practicum.dto.category.CategoryDto;
import ru.practicum.dto.category.NewCategoryDto;
import ru.practicum.ewm.stats.dto.EndpointHitDto;

import static ru.practicum.config.EWMServiceAppConfig.APP_NAME;

@RestController
@RequestMapping("/admin/categories")
@Validated
@RequiredArgsConstructor
public class CategoryAdminController {
    private final CategoryAdminService categoryAdminService;
    private final StatsClient statsClient;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto createCategory(@RequestBody @Valid NewCategoryDto newCategoryDto, HttpServletRequest request) {
        statsClient.createRecord(new EndpointHitDto(APP_NAME, request.getRequestURI(), request.getRemoteAddr()));
        return categoryAdminService.createCategory(newCategoryDto);
    }

    @PatchMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto updateCategory(@PathVariable @Positive @NotNull Long categoryId,
                                      @RequestBody @Valid NewCategoryDto newCategoryDto,
                                      HttpServletRequest request) {
        statsClient.createRecord(new EndpointHitDto(APP_NAME, request.getRequestURI(), request.getRemoteAddr()));
        return categoryAdminService.updateCategory(categoryId, newCategoryDto);
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable @Positive @NotNull Long categoryId, HttpServletRequest request) {
        statsClient.createRecord(new EndpointHitDto(APP_NAME, request.getRequestURI(), request.getRemoteAddr()));
        categoryAdminService.deleteCategory(categoryId);
    }
}
