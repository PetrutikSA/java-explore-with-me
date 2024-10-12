package ru.practicum.dto.event;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import ru.practicum.dto.category.CategoryMapper;
import ru.practicum.dto.location.LocationMapper;
import ru.practicum.dto.user.UserMapper;
import ru.practicum.model.Event;

import java.time.Instant;

import static ru.practicum.config.EWMServiceAppConfig.DATE_TIME_FORMATTER;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {LocationMapper.class, UserMapper.class, CategoryMapper.class})
public interface EventMapper {

    @Named("stringToInstant")
    default Instant stringToInstant(String instantString) {
        return DATE_TIME_FORMATTER.parse(instantString, Instant::from);
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "confirmedRequests", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "initiator", ignore = true)
    @Mapping(target = "publishedOn", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "views", ignore = true)
    @Mapping(target = "eventDate", source = "eventDate", qualifiedByName = "stringToInstant")
    Event newEventDtoToEvent(NewEventDto newEventDto);

    @Named("instantToString")
    default String instantToString(Instant instant) {
        return DATE_TIME_FORMATTER.format(instant);
    }

    @Mapping(target = "eventDate", source = "eventDate", qualifiedByName = "instantToString")
    @Mapping(target = "createdOn", source = "createdOn", qualifiedByName = "instantToString")
    @Mapping(target = "publishedOn", source = "publishedOn", qualifiedByName = "instantToString")
    EventFullDto eventToEventFullDto(Event event);

    @Mapping(target = "eventDate", source = "eventDate", qualifiedByName = "instantToString")
    EventShortDto eventToeventShortDto(Event event);
}
