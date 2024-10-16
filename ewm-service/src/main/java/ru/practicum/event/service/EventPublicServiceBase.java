package ru.practicum.event.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.category.repository.CategoryRepository;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.util.mapper.EventMapper;
import ru.practicum.dto.event.EventShortDto;
import ru.practicum.event.enums.EventsSort;
import ru.practicum.event.repository.EventRepository;
import ru.practicum.model.Category;
import ru.practicum.model.Event;
import ru.practicum.model.QEvent;
import ru.practicum.model.enums.EventState;
import ru.practicum.util.exception.NotFoundException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static ru.practicum.config.EWMServiceAppConfig.DATE_TIME_FORMATTER;

@Service
@RequiredArgsConstructor
public class EventPublicServiceBase implements EventPublicService {
    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;
    private final EventMapper eventMapper;

    @Override
    public List<EventShortDto> getPublicisedEventsWithFilter(String text,
                                                             List<Long> categoriesIds,
                                                             Boolean paid,
                                                             String rangeStartString,
                                                             String rangeEndString,
                                                             Boolean onlyAvailable, //def
                                                             String sort,
                                                             Integer from,
                                                             Integer size) {
        QEvent qEvent = QEvent.event;
        List<BooleanExpression> conditions = new ArrayList<>();
        conditions.add(qEvent.state.eq(EventState.PUBLISHED));

        if (text != null && !text.isBlank()) {
            conditions.add(qEvent.annotation.containsIgnoreCase(text).or(qEvent.description.containsIgnoreCase(text)));
        }
        if (categoriesIds != null && !categoriesIds.isEmpty()) {
            List<Category> categoryList = categoryRepository.findAllById(categoriesIds);
            conditions.add(qEvent.category.in(categoryList));
        }
        if (paid != null) {
            conditions.add(qEvent.paid.eq(paid));
        }
        if (rangeStartString != null && !rangeStartString.isBlank()) {
            Instant rangeStartInstant = DATE_TIME_FORMATTER.parse(rangeStartString, Instant::from);
            conditions.add(qEvent.eventDate.after(rangeStartInstant));
        }
        if (rangeEndString != null && !rangeEndString.isBlank()) {
            Instant rangeEndInstant = DATE_TIME_FORMATTER.parse(rangeEndString, Instant::from);
            conditions.add(qEvent.eventDate.before(rangeEndInstant));
        }

        Pageable pageable;
        if (sort != null) {
            EventsSort eventsSort = EventsSort.valueOf(sort);
            Sort sortResults;
            switch (eventsSort) {
                case EVENT_DATE -> sortResults = Sort.by("eventDate").ascending();
                case VIEWS -> sortResults = Sort.by("views").ascending();
                default -> sortResults = Sort.by("publishedOn").descending();
            }
            pageable = PageRequest.of(from, size, sortResults);
        } else {
            pageable = PageRequest.of(from, size);
        }

        BooleanExpression finalCondition = conditions.stream()
                .reduce(BooleanExpression::and)
                .get();
        Page<Event> result = eventRepository.findAll(finalCondition, pageable);

        return result.stream()
                .filter((onlyAvailable) ?
                        event -> (event.getConfirmedRequests() < event.getParticipantLimit()) :
                        event -> true)
                .map(eventMapper::eventToeventShortDto)
                .toList();
    }

    @Override
    public EventFullDto getEventById(Long eventId, boolean uniqueRequest) {
        Event event = eventRepository.findByIdAndState(eventId, EventState.PUBLISHED)
                .orElseThrow(() -> new NotFoundException(eventId, Event.class));
        if (uniqueRequest) {
            long views = event.getViews() + 1;
            event.setViews(views);
            event = eventRepository.save(event);
        }
        return eventMapper.eventToEventFullDto(event);
    }
}
