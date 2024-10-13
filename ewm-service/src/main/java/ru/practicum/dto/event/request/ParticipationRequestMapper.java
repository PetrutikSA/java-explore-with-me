package ru.practicum.dto.event.request;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.practicum.dto.event.EventMapper;
import ru.practicum.dto.user.UserMapper;
import ru.practicum.model.ParticipationRequest;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class, EventMapper.class})
public interface ParticipationRequestMapper {

    @Mapping(target = "event", expression = "java(participationRequest.getEvent().getId())")
    @Mapping(target = "requester", expression = "java(participationRequest.getRequester().getId())")
    ParticipationRequestDto participationRequestToParticipationRequestDto(ParticipationRequest participationRequest);
}
