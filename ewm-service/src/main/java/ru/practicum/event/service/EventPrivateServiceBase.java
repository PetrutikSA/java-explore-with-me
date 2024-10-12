package ru.practicum.event.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.category.repository.CategoryRepository;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.EventMapper;
import ru.practicum.dto.event.EventShortDto;
import ru.practicum.dto.event.NewEventDto;
import ru.practicum.dto.event.UpdateEventUserRequest;
import ru.practicum.dto.event.request.EventRequestStatusUpdateRequest;
import ru.practicum.dto.event.request.EventRequestStatusUpdateResult;
import ru.practicum.dto.event.request.ParticipationRequestDto;
import ru.practicum.dto.location.LocationDto;
import ru.practicum.dto.location.LocationMapper;
import ru.practicum.event.repository.EventRepository;
import ru.practicum.event.repository.LocationRepository;
import ru.practicum.model.Category;
import ru.practicum.model.Event;
import ru.practicum.model.Location;
import ru.practicum.model.User;
import ru.practicum.request.repository.RequestRepository;
import ru.practicum.user.repository.UserRepository;
import ru.practicum.util.exception.NotFoundException;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventPrivateServiceBase implements EventPrivateService {
    private final EventRepository eventRepository;
    private final RequestRepository requestRepository;
    private final LocationRepository locationRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    private final EventMapper eventMapper;
    private final LocationMapper locationMapper;

    @Override
    public EventFullDto createNewEvent(Long userId, NewEventDto newEventDto) {
        User initiator = findUserByIdOrThrowNotFoundException(userId);
        Category category = findCategoryByIdOrThrowNotFoundException(newEventDto.getCategory());
        Event event = eventMapper.newEventDtoToEvent(newEventDto);

        event.setInitiator(initiator);
        event.setCategory(category);
        Location location = event.getLocation();
        if (location != null) {
            location = locationRepository.save(location);
            event.setLocation(location);
        }
        event.setCreatedOn(Instant.now());
        event = eventRepository.save(event);
        return eventMapper.eventToEventFullDto(event);
    }

    @Override
    public List<EventShortDto> getAllUserEvents(Long userId, Integer from, Integer size) {
        findUserByIdOrThrowNotFoundException(userId);
        Pageable pageable = PageRequest.of(from, size);
        Page<Event> eventPage = eventRepository.findAllByInitiatorId(userId, pageable);
        return eventPage.stream()
                .map(eventMapper::eventToeventShortDto)
                .toList();
    }

    @Override
    public EventFullDto getEventById(Long userId, Long eventId) {
        Event event = findEventByIdOrThrowNotFoundException(eventId);
        return eventMapper.eventToEventFullDto(event);
    }

    @Override
    public EventFullDto updateEventByUser(Long userId, Long eventId, UpdateEventUserRequest updateEventUserRequest) {
        findUserByIdOrThrowNotFoundException(userId);
        Event event = findEventByIdOrThrowNotFoundException(eventId);
        eventMapper.updateEventUserRequestIgnoringLocationAndCategoryId(updateEventUserRequest, event);
        LocationDto newLocationDto = updateEventUserRequest.getLocation();
        if (newLocationDto != null) {
            Location location = event.getLocation();
            locationMapper.updateLocationWithLocationDto(newLocationDto, location);
            location = locationRepository.save(location);
            event.setLocation(location);
        }
        Long newCategoryId = updateEventUserRequest.getCategoryId();
        if (newCategoryId != null && newCategoryId != 0) {
            Category newCategory = findCategoryByIdOrThrowNotFoundException(newCategoryId);
            event.setCategory(newCategory);
        }
        event = eventRepository.save(event);
        return eventMapper.eventToEventFullDto(event);
    }

    @Override
    public List<ParticipationRequestDto> getParticipationRequestsOnEvent(Long userId, Long eventId) {
        return List.of();
    }

    @Override
    public EventRequestStatusUpdateResult responseOnParticipationRequests(
            Long userId, Long eventId, EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest) {
        return null;
    }

    private User findUserByIdOrThrowNotFoundException(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(userId, User.class));
    }

    private Category findCategoryByIdOrThrowNotFoundException(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException(categoryId, Category.class));
    }

    private Event findEventByIdOrThrowNotFoundException(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException(eventId, Event.class));
    }
}
