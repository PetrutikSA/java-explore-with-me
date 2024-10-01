package ru.practicum.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.practicum.ewm.stats.dto.EndpointHitDto;
import ru.practicum.model.EndpointHit;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EndpointHitMapper {
    EndpointHit endpointHitDtoToEndpointHit(EndpointHitDto endpointHitDto);
    EndpointHitDto endpointHitToEndpointHitDto(EndpointHit endpointHit);
}
