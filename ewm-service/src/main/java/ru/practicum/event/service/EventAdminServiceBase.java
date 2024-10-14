package ru.practicum.event.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.category.repository.CategoryRepository;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.EventMapper;
import ru.practicum.dto.event.UpdateEventAdminRequest;
import ru.practicum.dto.event.enums.StateActionAdmin;
import ru.practicum.dto.location.LocationDto;
import ru.practicum.dto.location.LocationMapper;
import ru.practicum.event.repository.EventRepository;
import ru.practicum.event.repository.LocationRepository;
import ru.practicum.model.Category;
import ru.practicum.model.Event;
import ru.practicum.model.Location;
import ru.practicum.model.QEvent;
import ru.practicum.model.enums.EventState;
import ru.practicum.util.exception.NotFoundException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static ru.practicum.config.EWMServiceAppConfig.DATE_TIME_FORMATTER;

@Service
@RequiredArgsConstructor
public class EventAdminServiceBase implements EventAdminService {
    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;
    private final EventMapper eventMapper;
    private final LocationMapper locationMapper;

    @Override
    public List<EventFullDto> getAllEventsWithFilter(List<Long> usersIds,
                                                     List<String> states,
                                                     List<Long> categoriesIds,
                                                     String rangeStartString,
                                                     String rangeEndString,
                                                     Integer from,
                                                     Integer size) {
        Pageable pageable = PageRequest.of(from, size);
        QEvent qEvent = QEvent.event;
        List<BooleanExpression> conditions = new ArrayList<>();
        if (usersIds != null && !usersIds.isEmpty()) {
            conditions.add(qEvent.initiator.id.in(usersIds));
        }
        if (states != null && !states.isEmpty()) {
            List<EventState> eventStates = states.stream()
                    .map(EventState::valueOf)
                    .toList();
            conditions.add(qEvent.state.in(eventStates));
        }
        if (categoriesIds != null && !categoriesIds.isEmpty()) {
            List<Category> categoryList = categoryRepository.findAllById(categoriesIds);
            conditions.add(qEvent.category.in(categoryList));
        }
        if (rangeStartString != null && !rangeStartString.isBlank()) {
            Instant rangeStartInstant = DATE_TIME_FORMATTER.parse(rangeStartString, Instant::from);
            conditions.add(qEvent.eventDate.after(rangeStartInstant));
        }
        if (rangeEndString != null && !rangeEndString.isBlank()) {
            Instant rangeEndInstant = DATE_TIME_FORMATTER.parse(rangeEndString, Instant::from);
            conditions.add(qEvent.eventDate.before(rangeEndInstant));
        }

        Page<Event> result;
        if (conditions.isEmpty()) {
            result = eventRepository.findAll(pageable);
        } else {
            BooleanExpression finalCondition = conditions.stream()
                    .reduce(BooleanExpression::and)
                    .get();
            result = eventRepository.findAll(finalCondition, pageable);
        }

        return result.stream()
                .map(eventMapper::eventToEventFullDto)
                .toList();
    }

    @Override
    public EventFullDto updateEventByAdmin(Long eventId, UpdateEventAdminRequest updateEventAdminRequest) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException(eventId, Event.class));
        eventMapper.updateEventAdminRequestIgnoringLocationAndCategoryId(updateEventAdminRequest, event);
        LocationDto newLocationDto = updateEventAdminRequest.getLocation();
        if (newLocationDto != null) {
            Location location = event.getLocation();
            locationMapper.updateLocationWithLocationDto(newLocationDto, location);
            location = locationRepository.save(location);
            event.setLocation(location);
        }
        Long newCategoryId = updateEventAdminRequest.getCategoryId();
        if (newCategoryId != null && newCategoryId != 0) {
            Category newCategory = categoryRepository.findById(newCategoryId)
                    .orElseThrow(() -> new NotFoundException(newCategoryId, Category.class));
            event.setCategory(newCategory);
        }
        if (updateEventAdminRequest.getStateAction() != null) {
            StateActionAdmin stateActionAdmin = StateActionAdmin.valueOf(updateEventAdminRequest.getStateAction());
            switch (stateActionAdmin) {
                case PUBLISH_EVENT -> event.setState(EventState.PUBLISHED);
                case REJECT_EVENT -> event.setState(EventState.CANCELED);
            }
        }
        event = eventRepository.save(event);
        return eventMapper.eventToEventFullDto(event);
    }
}
