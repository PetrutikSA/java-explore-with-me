package ru.practicum.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ApiError {
    @JsonIgnore
    private List<StackTraceElement> errors;
    private String message;
    private String reason;
    private String status;
    private String timestamp;
}
