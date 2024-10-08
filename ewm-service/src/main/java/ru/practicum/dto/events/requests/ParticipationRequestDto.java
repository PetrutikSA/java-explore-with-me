package ru.practicum.dto.events.requests;

import lombok.Data;

@Data
public class ParticipationRequestDto {
    private Long id;
    private String created;
    private Long event; //event id
    private Long requester; //requester id
    private String status;
}
