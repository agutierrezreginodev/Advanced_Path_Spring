package com.codeup.eventify.domain.ports.in.events;

import com.codeup.eventify.domain.model.Event;

public interface UpdateEventUseCase {
    Event updateEvent(Long id, Event event);
}
