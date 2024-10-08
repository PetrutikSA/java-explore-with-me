package ru.practicum.dto.event.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import ru.practicum.dto.event.enums.EventStatus;
import ru.practicum.dto.validation.enums.EnumValidator;

import java.util.List;

public class EventRequestStatusUpdateRequest {
    @Positive
    @NotNull
    private List<Long> requestIds;
    @EnumValidator(enumClazz = EventStatus.class)
    private String status;
}
