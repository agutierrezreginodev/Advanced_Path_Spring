package com.codeup.eventify.application.usecase.events;

import org.springframework.stereotype.Service;

import com.codeup.eventify.domain.model.Event;
import com.codeup.eventify.domain.ports.in.events.UpdateEventUseCase;
import com.codeup.eventify.domain.ports.out.EventRepositoryPort;
import com.codeup.eventify.domain.ports.out.VenueRepositoryPort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UpdateEventUseCaseImpl implements UpdateEventUseCase {
    private final EventRepositoryPort eventRepository;
    private final VenueRepositoryPort venueRepository;

    public UpdateEventUseCaseImpl(EventRepositoryPort eventRepository, VenueRepositoryPort venueRepository) {
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
    }

    @Override
    public Event updateEvent(Long id, Event event) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid event ID");
        }
        if (event.getTitle() == null || event.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Event title cannot be null or empty");
        }
        if (event.getDescription() == null || event.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Event description cannot be null or empty");
        }
        if (event.getDate() == null || event.getDate().isEmpty()) {
            throw new IllegalArgumentException("Event date cannot be null or empty");
        }
        if (event.getHostedBy() == null || event.getHostedBy().isEmpty()) {
            throw new IllegalArgumentException("Event must have a host");
        }
        log.info("Updating event with ID: {}", id);

        var venue = venueRepository.findById(event.getVenue().getId())
                .orElseThrow(() -> new RuntimeException("Venue not found with ID: " + event.getVenue().getId()));

        Event updatedEvent = Event.builder()
                .id(id)
                .title(event.getTitle())
                .description(event.getDescription())
                .date(event.getDate())
                .venue(venue)
                .hour(event.getHour())
                .price(event.getPrice())
                .hostedBy(event.getHostedBy())
                .build();

        Event savedEvent = eventRepository.save(updatedEvent);
        log.info("Event updated successfully with ID: {}", id);
        return savedEvent;
    }
}
