package ru.practicum.comment;

import ru.practicum.dto.comment.CommentDto;
import ru.practicum.dto.comment.NewCommentDto;
import ru.practicum.event.TestObjectsEvent;
import ru.practicum.model.Comment;
import ru.practicum.user.TestObjectsUser;

import java.time.Instant;

import static ru.practicum.config.EWMServiceAppConfig.DATE_TIME_FORMATTER;

public class TestObjectsComment {
    public Instant created = Instant.now();
    public Comment comment;
    public CommentDto commentDto;
    public NewCommentDto newCommentDto;
    public NewCommentDto updateCommentDto;


    public TestObjectsComment(TestObjectsUser testObjectsUser, TestObjectsEvent testObjectsEvent) {
        newCommentDto = new NewCommentDto();
        newCommentDto.setText("Text for comment");

        updateCommentDto = new NewCommentDto();
        updateCommentDto.setText("Updated text for comment");

        comment = new Comment();
        comment.setId(1L);
        comment.setText(newCommentDto.getText());
        comment.setEvent(testObjectsEvent.event);
        comment.setUser(testObjectsUser.user);
        comment.setCreated(created);

        commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setText(comment.getText());
        commentDto.setEventId(comment.getEvent().getId());
        commentDto.setUserId(comment.getUser().getId());
        commentDto.setCreated(DATE_TIME_FORMATTER.format(created));
    }
}
