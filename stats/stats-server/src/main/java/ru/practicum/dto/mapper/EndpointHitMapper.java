package ru.practicum.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.practicum.ewm.stats.dto.EndpointHitDto;
import ru.practicum.model.EndpointHit;

import java.time.Instant;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = Instant.class)
public interface EndpointHitMapper {

    @Mapping(target = "timestamp",
            expression = "java(ru.practicum.ewm.stats.util.Constants.DATE_TIME_FORMATTER" +
                    ".parse(endpointHitDto.getTimestamp(), Instant::from))")
    EndpointHit endpointHitDtoToEndpointHit(EndpointHitDto endpointHitDto);
    @Mapping(target = "timestamp", expression = "java(ru.practicum.ewm.stats.util.Constants.DATE_TIME_FORMATTER" +
            ".format(endpointHit.getTimestamp()))")
    EndpointHitDto endpointHitToEndpointHitDto(EndpointHit endpointHit);
}
