package com.codeup.eventify.infrastructure.adapters.out.jpa;

import com.codeup.eventify.domain.model.Venue;
import com.codeup.eventify.domain.ports.out.VenueRepositoryPort;
import com.codeup.eventify.infrastructure.adapters.out.jpa.entity.VenueEntity;
import com.codeup.eventify.infrastructure.adapters.out.jpa.mapper.VenueMapper;
import com.codeup.eventify.infrastructure.adapters.out.jpa.repository.SpringVenueRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VenueJpaAdapter implements VenueRepositoryPort {

    private final SpringVenueRepository springVenueRepository;
    private final VenueMapper venueMapper;

    @Override
    public Venue save(Venue venue) {
        VenueEntity venueEntity = venueMapper.toEntity(venue);
        VenueEntity savedVenue = springVenueRepository.save(venueEntity);
        return venueMapper.toDomain(savedVenue);
    }

    @Override
    public Optional<Venue> findById(Long id) {
        return springVenueRepository.findById(id)
                .map(venueMapper::toDomain);
    }

    @Override
    public List<Venue> findAll() {
        return springVenueRepository.findAll()
                .stream()
                .map(venueMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Venue> findByName(String name) {
        return springVenueRepository.findByName(name)
                .map(venueMapper::toDomain);
    }

    @Override
    public boolean existsByName(String name) {
        return springVenueRepository.existsByName(name);
    }

    @Override
    public void deleteById(Long id) {
        springVenueRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return springVenueRepository.existsById(id);
    }

    @Override
    public Venue update(Long id, Venue venue) {
        // Verify venue exists before updating
        if (!springVenueRepository.existsById(id)) {
            throw new RuntimeException("Venue not found with id: " + id);
        }

        VenueEntity venueEntity = venueMapper.toEntity(venue);
        venueEntity.setId(id);

        VenueEntity updatedVenue = springVenueRepository.save(venueEntity);
        return venueMapper.toDomain(updatedVenue);
    }
}
