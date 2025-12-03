package com.codeup.eventify.infrastructure.adapters.out.jpa;

import com.codeup.eventify.domain.model.Event;
import com.codeup.eventify.domain.ports.out.EventRepositoryPort;
import com.codeup.eventify.infrastructure.adapters.out.jpa.entity.EventEntity;
import com.codeup.eventify.infrastructure.adapters.out.jpa.mapper.EventMapper;
import com.codeup.eventify.infrastructure.adapters.out.jpa.repository.SpringEventRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EventJpaAdapter implements EventRepositoryPort {

    private final SpringEventRepository springEventRepository;
    private final EventMapper eventMapper;

    @Override
    public Event save(Event event) {
        EventEntity eventEntity = eventMapper.toEntity(event);
        EventEntity savedEvent = springEventRepository.save(eventEntity);
        return eventMapper.toDomain(savedEvent);
    }

    @Override
    public Optional<Event> findById(Long id) {
        return springEventRepository.findById(id)
                .map(eventMapper::toDomain);
    }

    @Override
    public List<Event> findAll() {
        return springEventRepository.findAll()
                .stream()
                .map(eventMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> findByTitle(String title) {
        return springEventRepository.findByTitle(title)
                .stream()
                .map(eventMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> findByVenueId(Long venueId) {
        return springEventRepository.findByVenueId(venueId)
                .stream()
                .map(eventMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> findByHostedBy(String hostedBy) {
        return springEventRepository.findByHostedBy(hostedBy)
                .stream()
                .map(eventMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByTitle(String title) {
        return springEventRepository.existsByTitle(title);
    }

    @Override
    public void deleteById(Long id) {
        springEventRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return springEventRepository.existsById(id);
    }
}
