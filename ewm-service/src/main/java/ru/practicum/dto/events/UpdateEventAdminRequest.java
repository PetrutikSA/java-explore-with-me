package ru.practicum.dto.events;

import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.practicum.dto.events.enums.StateActionAdmin;
import ru.practicum.dto.locations.LocationDto;
import ru.practicum.dto.validation.enums.EnumValidator;

@Data
public class UpdateEventAdminRequest {
    @Length(min = 20, max = 2000)
    private String annotation;
    @Positive
    private int category_id;
    @Length(min = 20, max = 7000)
    private String description;
    private String eventDate;
    private LocationDto location;
    private Boolean paid;
    private Integer participantLimit;
    private Boolean requestModeration;
    @EnumValidator(enumClazz = StateActionAdmin.class)
    private String stateAction;
    @Length(min = 3, max = 120)
    private String title;
}
