package ru.practicum.comment.service;

import org.springframework.stereotype.Service;
import ru.practicum.dto.comment.CommentDto;

import java.util.List;

@Service
public class CommentPublicServiceBase implements CommentPublicService {
    @Override
    public List<CommentDto> getEventComment(Long eventId, Integer from, Integer size) {
        return List.of();
    }

    @Override
    public CommentDto getCommentById(Long eventId, Long commentId) {
        return null;
    }
}
