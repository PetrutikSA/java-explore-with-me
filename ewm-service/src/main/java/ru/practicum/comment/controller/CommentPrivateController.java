package ru.practicum.comment.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.dto.comment.CommentDto;
import ru.practicum.dto.comment.NewCommentDto;

@RestController
@RequestMapping("/users/{userId}/events/{eventId}/comments")
@RequiredArgsConstructor
@Validated
public class CommentPrivateController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto createComment(@PathVariable @Positive @NotNull Long userId,
                                    @PathVariable @Positive @NotNull Long eventId,
                                    @RequestBody @Valid NewCommentDto newCommentDto) {
        return null;
    }

    @PatchMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto updateComment(@PathVariable @Positive @NotNull Long userId,
                                    @PathVariable @Positive @NotNull Long eventId,
                                    @PathVariable @Positive @NotNull Long commentId,
                                    @RequestBody @Valid NewCommentDto newCommentDto) {
        return null;
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable @Positive @NotNull Long userId,
                              @PathVariable @Positive @NotNull Long eventId,
                              @PathVariable @Positive @NotNull Long commentId) {

    }
}
