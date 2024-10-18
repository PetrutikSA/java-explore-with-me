package ru.practicum.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.comment.repository.CommentRepository;
import ru.practicum.model.Comment;
import ru.practicum.util.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class CommentAdminServiceBase implements CommentAdminService {
    private final CommentRepository commentRepository;

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException(commentId, Comment.class));
        commentRepository.deleteById(commentId);
    }
}
