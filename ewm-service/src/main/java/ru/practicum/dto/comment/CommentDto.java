package ru.practicum.dto.comment;

import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private Long userId;
    private Long eventId;
    private String created;
    private String text;
}
