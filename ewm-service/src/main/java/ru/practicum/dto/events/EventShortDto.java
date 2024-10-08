package ru.practicum.dto.events;

import lombok.Data;
import ru.practicum.dto.categories.CategoryDto;
import ru.practicum.dto.users.UserShortDto;

@Data
public class EventShortDto {
    private Long id;
    private String annotation;
    private CategoryDto category;
    private int confirmedRequests;
    private String eventDate;
    private UserShortDto initiator;
    private boolean paid;
    private String title;
    private long views;
}
