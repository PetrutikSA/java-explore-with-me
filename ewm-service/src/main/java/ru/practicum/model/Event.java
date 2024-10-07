package ru.practicum.model;

import ru.practicum.model.enums.EventState;

import java.time.Instant;

public class Event {
    private Long id;
    private String annotation;
    private Category category;
    private int confirmedRequests;
    private Instant createdOn;
    private String description;
    private Instant eventDate;
    private User initiator;
    private Location location;
    private boolean paid;
    private int participantLimit;
    private Instant publishedOn;
    private boolean requestModeration;
    private EventState state;
    private String title;
    private long views;
}
