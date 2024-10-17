package ru.practicum.comment.controller;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.comment.service.CommentAdminService;

@RestController
@RequestMapping("/admin/comments")
@RequiredArgsConstructor
@Validated
public class CommentAdminController {
    private final CommentAdminService commentAdminService;

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable @Positive @NotNull Long commentId) {
        commentAdminService.deleteComment(commentId);
    }
}
