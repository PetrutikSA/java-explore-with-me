package ru.practicum.comment.service;

import org.springframework.stereotype.Service;
import ru.practicum.dto.comment.CommentDto;
import ru.practicum.dto.comment.NewCommentDto;

@Service
public class CommentPrivateServiceBase implements CommentPrivateService {
    @Override
    public CommentDto createComment(Long userId, Long eventId, NewCommentDto newCommentDto) {
        return null;
    }

    @Override
    public CommentDto updateComment(Long userId, Long eventId, Long commentId, NewCommentDto newCommentDto) {
        return null;
    }

    @Override
    public void deleteComment(Long userId, Long eventId, Long commentId) {

    }
}
