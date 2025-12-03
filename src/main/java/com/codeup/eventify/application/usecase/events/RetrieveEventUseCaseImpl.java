package com.codeup.eventify.application.usecase.events;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codeup.eventify.domain.model.Event;
import com.codeup.eventify.domain.ports.in.events.RetrieveEventUseCase;
import com.codeup.eventify.domain.ports.out.EventRepositoryPort;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RetrieveEventUseCaseImpl implements RetrieveEventUseCase {
    private final EventRepositoryPort eventRepository;

    public RetrieveEventUseCaseImpl(EventRepositoryPort eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event getEventById(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid event ID");
        }
        log.info("Fetching event with ID: {}", id);

        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with ID: " + id));
    }

    @Override
    public List<Event> getAllEvents() {
        log.info("Fetching all events");
        return eventRepository.findAll();
    }

    @Override
    public List<Event> getEventsByVenueId(Long venueId) {
        if (venueId <= 0) {
            throw new IllegalArgumentException("Invalid venue ID");
        }
        log.info("Fetching events for venue ID: {}", venueId);

        return eventRepository.findByVenueId(venueId);
    }
}
