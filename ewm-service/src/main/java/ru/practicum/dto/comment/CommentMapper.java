package ru.practicum.dto.comment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.practicum.model.Comment;

import java.time.Instant;

import static ru.practicum.config.EWMServiceAppConfig.DATE_TIME_FORMATTER;

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

    @Named("instantToString")
    default String instantToString(Instant instant) {
        return (instant != null) ? DATE_TIME_FORMATTER.format(instant) : null;
    }
}
