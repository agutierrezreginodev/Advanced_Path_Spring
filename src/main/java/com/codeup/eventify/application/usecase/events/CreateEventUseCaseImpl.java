package com.codeup.eventify.application.usecase.events;

import com.codeup.eventify.domain.model.Event;
import com.codeup.eventify.domain.ports.in.events.CreateEventUseCase;
import com.codeup.eventify.domain.ports.out.EventRepositoryPort;
import com.codeup.eventify.domain.ports.out.VenueRepositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CreateEventUseCaseImpl implements CreateEventUseCase {

    private final EventRepositoryPort eventRepository;
    private final VenueRepositoryPort venueRepository;

    public CreateEventUseCaseImpl(EventRepositoryPort eventRepository, VenueRepositoryPort venueRepository) {
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
    }

    @Override
    public Event createEvent(Event event) {
        log.info("Creating event with title: {}", event.getTitle());

        if (eventRepository.existsByTitle(event.getTitle())) {
            throw new IllegalArgumentException("An event with title '" + event.getTitle() + "' already exists");
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

        // Note: venue should be resolved at the adapter layer and passed as a complete
        // Venue object
        // For now, we'll handle the case where venue might be null
        if (event.getVenue() == null) {
            throw new IllegalArgumentException("Event must have an associated venue");
        }

        var venue = venueRepository.findById(event.getVenue().getId())
                .orElseThrow(() -> new RuntimeException("Venue not found with ID: " + event.getVenue().getId()));

        Event createdEvent = Event.builder()
                .title(event.getTitle())
                .description(event.getDescription())
                .date(event.getDate())
                .venue(venue)
                .hour(event.getHour())
                .price(event.getPrice())
                .hostedBy(event.getHostedBy())
                .build();

        Event savedEvent = eventRepository.save(createdEvent);
        log.info("Event created successfully with ID: {}", savedEvent.getId());
        return savedEvent;
    }
}
