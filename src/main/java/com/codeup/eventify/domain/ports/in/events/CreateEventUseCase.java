package com.codeup.eventify.domain.ports.in.events;

import com.codeup.eventify.domain.model.Event;

public interface CreateEventUseCase {
    Event createEvent(Event event);
}
