package ru.practicum.dto.event;

import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.practicum.dto.location.LocationDto;

@Data
public class NewEventDto {
    @Length(min = 20, max = 2000)
    private String annotation;
    @Positive
    private int category_id;
    @Length(min = 20, max = 7000)
    private String description;
    private String eventDate;
    private LocationDto location;
    private boolean paid = false;
    private int participantLimit = 0;
    private boolean requestModeration = true;
    @Length(min = 3, max = 120)
    private String title;
}
