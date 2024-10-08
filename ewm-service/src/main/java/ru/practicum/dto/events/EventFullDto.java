package ru.practicum.dto.events;

import lombok.Data;
import ru.practicum.dto.categories.CategoryDto;
import ru.practicum.dto.locations.LocationDto;
import ru.practicum.dto.users.UserShortDto;

@Data
public class EventFullDto {
    private Long id;
    private String annotation;
    private CategoryDto category;
    private int confirmedRequests;
    private String createdOn;
    private String description;
    private String eventDate;
    private UserShortDto initiator;
    private LocationDto location;
    private boolean paid;
    private int participantLimit;
    private String publishedOn;
    private boolean requestModeration;
    private String state;
    private String title;
    private long views;
}
