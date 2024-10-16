package ru.practicum.dto.comment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.model.Comment;

@Mapper
public interface CommentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "event", ignore = true)
    Comment newCommentDtoToComment(NewCommentDto newCommentDto);

    @Mapping(target = "created", source = "created", qualifiedByName = "instantToString")
    @Mapping(target = "eventId", expression = "java(comment.getEvent().getId())")
    @Mapping(target = "userId", expression = "java(comment.getUser().getId())")
    CommentDto commentToCommentDto(Comment comment);
}
