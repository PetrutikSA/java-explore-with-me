package ru.practicum.dto.event.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import ru.practicum.dto.event.enums.ParticipationRequestUpdateStatus;
import ru.practicum.dto.validation.enums.EnumValidator;

import java.util.List;

@Data
public class EventRequestStatusUpdateRequest {
    @Positive
    @NotNull
    private List<Long> requestIds;
    @EnumValidator(enumClazz = ParticipationRequestUpdateStatus.class)
    private String status;
}
