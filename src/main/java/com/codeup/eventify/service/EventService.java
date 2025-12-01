package com.codeup.eventify.service;

import com.codeup.eventify.web.dto.request.EventRequestDTO;
import com.codeup.eventify.web.dto.response.EventResponseDTO;
import com.codeup.eventify.entity.EventEntity;
import com.codeup.eventify.entity.VenueEntity;
import com.codeup.eventify.mapper.EventMapper;
import com.codeup.eventify.repository.EventRepository;
import com.codeup.eventify.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    private final EventMapper eventMapper;

    @Transactional
    public EventResponseDTO createEvent(EventRequestDTO requestDTO) {
        log.info("Creating event with title: {}", requestDTO.getTitle());

        if (eventRepository.existsByTitle(requestDTO.getTitle())) {
            throw new IllegalArgumentException("An event with title '" + requestDTO.getTitle() + "' already exists");
        }
        if (requestDTO.getTitle() == null || requestDTO.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Event title cannot be null or empty");
        }
        if (requestDTO.getDescription() == null || requestDTO.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Event description cannot be null or empty");
        }
        if (requestDTO.getDate() == null || requestDTO.getDate().isEmpty()) {
            throw new IllegalArgumentException("Event date cannot be null or empty");
        }
        if (requestDTO.getHostedBy() == null || requestDTO.getHostedBy().isEmpty()) {
            throw new IllegalArgumentException("Event must have a host");
        }

        VenueEntity venue = venueRepository.findById(requestDTO.getVenueId())
                .orElseThrow(() -> new RuntimeException("Venue not found with ID: " + requestDTO.getVenueId()));

        EventEntity event = eventMapper.toEntity(requestDTO, venue);
        EventEntity savedEvent = eventRepository.save(event);

        log.info("Event created successfully with ID: {}", savedEvent.getId());
        return eventMapper.toResponseDTO(savedEvent);
    }

    @Transactional(readOnly = true)
    public EventResponseDTO getEventById(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid event ID");
        }
        log.info("Fetching event with ID: {}", id);

        EventEntity event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with ID: " + id));

        return eventMapper.toResponseDTO(event);
    }

    @Transactional(readOnly = true)
    public List<EventResponseDTO> getAllEvents() {
        log.info("Fetching all events");
        return eventRepository.findAll()
                .stream()
                .map(eventMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EventResponseDTO> getEventsByVenueId(Long venueId) {
        if (venueId <= 0) {
            throw new IllegalArgumentException("Invalid venue ID");
        }
        log.info("Fetching events for venue ID: {}", venueId);

        return eventRepository.findByVenueId(venueId)
                .stream()
                .map(eventMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public EventResponseDTO updateEvent(Long id, EventRequestDTO requestDTO) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid event ID");
        }
        if (requestDTO.getTitle() == null || requestDTO.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Event title cannot be null or empty");
        }
        if (requestDTO.getDescription() == null || requestDTO.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Event description cannot be null or empty");
        }
        if (requestDTO.getDate() == null || requestDTO.getDate().isEmpty()) {
            throw new IllegalArgumentException("Event date cannot be null or empty");
        }
        if (requestDTO.getHostedBy() == null || requestDTO.getHostedBy().isEmpty()) {
            throw new IllegalArgumentException("Event must have a host");
        }
        log.info("Updating event with ID: {}", id);

        EventEntity event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with ID: " + id));

        VenueEntity venue = venueRepository.findById(requestDTO.getVenueId())
                .orElseThrow(() -> new RuntimeException("Venue not found with ID: " + requestDTO.getVenueId()));

        eventMapper.updateEntityFromDTO(requestDTO, event, venue);
        EventEntity updatedEvent = eventRepository.save(event);

        log.info("Event updated successfully with ID: {}", id);
        return eventMapper.toResponseDTO(updatedEvent);
    }

    @Transactional
    public void deleteEvent(Long id) {
        log.info("Deleting event with ID: {}", id);

        if (!eventRepository.existsById(id)) {
            throw new RuntimeException("Event not found with ID: " + id);
        }

        eventRepository.deleteById(id);
        log.info("Event deleted successfully with ID: {}", id);
    }

}