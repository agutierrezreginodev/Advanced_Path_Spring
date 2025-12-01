package com.codeup.eventify.service;

import com.codeup.eventify.web.dto.request.VenueRequestDTO;
import com.codeup.eventify.web.dto.response.VenueResponseDTO;
import com.codeup.eventify.entity.VenueEntity;
import com.codeup.eventify.mapper.VenueMapper;
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
public class VenueService {

    private final VenueRepository venueRepository;
    private final VenueMapper venueMapper;

    @Transactional
    public VenueResponseDTO createVenue(VenueRequestDTO requestDTO) {
        if (venueRepository.existsByName(requestDTO.getName())) {
            throw new RuntimeException("Venue already exists with name: " + requestDTO.getName());
        }
        if (requestDTO.getName() == null || requestDTO.getName().isEmpty()) {
            throw new IllegalArgumentException("Venue name cannot be null or empty");
        }
        if (requestDTO.getCountry() == null || requestDTO.getCountry().isEmpty()) {
            throw new IllegalArgumentException("Venue country cannot be null or empty");
        }
        if (requestDTO.getAddress() == null || requestDTO.getAddress().isEmpty()) {
            throw new IllegalArgumentException("Venue address cannot be null or empty");
        }
        if (requestDTO.getCity() == null || requestDTO.getCity().isEmpty()) {
            throw new IllegalArgumentException("Venue city cannot be null or empty");
        }
        if (requestDTO.getState() == null || requestDTO.getState().isEmpty()) {
            throw new IllegalArgumentException("Venue state cannot be null or empty");
        }
        if (requestDTO.getZipCode() == null || requestDTO.getZipCode().isEmpty()) {
            throw new IllegalArgumentException("Venue zip code cannot be null or empty");
        }
        log.info("Creating venue with name: {}", requestDTO.getName());

        VenueEntity venue = venueMapper.toEntity(requestDTO);
        VenueEntity savedVenue = venueRepository.save(venue);

        log.info("Venue created successfully with ID: {}", savedVenue.getId());
        return venueMapper.toResponseDTO(savedVenue);
    }

    @Transactional(readOnly = true)
    public VenueResponseDTO getVenueById(Long id) {
        if (!venueRepository.existsById(id)) {
            throw new RuntimeException("Venue not found with ID: " + id);
        }
        log.info("Fetching venue with ID: {}", id);

        VenueEntity venue = venueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venue not found with ID: " + id));

        return venueMapper.toResponseDTO(venue);
    }

    @Transactional(readOnly = true)
    public List<VenueResponseDTO> getAllVenues() {
        log.info("Fetching all venues");

        return venueRepository.findAll()
                .stream()
                .map(venueMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public VenueResponseDTO updateVenue(Long id, VenueRequestDTO requestDTO) {
        if (!venueRepository.existsById(id)) {
            throw new RuntimeException("Venue not found with ID: " + id);
        }
        if (requestDTO.getName() == null || requestDTO.getName().isEmpty()) {
            throw new IllegalArgumentException("Venue name cannot be null or empty");
        }
        if (requestDTO.getCountry() == null || requestDTO.getCountry().isEmpty()) {
            throw new IllegalArgumentException("Venue country cannot be null or empty");
        }
        if (requestDTO.getAddress() == null || requestDTO.getAddress().isEmpty()) {
            throw new IllegalArgumentException("Venue address cannot be null or empty");
        }
        if (requestDTO.getCity() == null || requestDTO.getCity().isEmpty()) {
            throw new IllegalArgumentException("Venue city cannot be null or empty");
        }
        if (requestDTO.getState() == null || requestDTO.getState().isEmpty()) {
            throw new IllegalArgumentException("Venue state cannot be null or empty");
        }
        if (requestDTO.getZipCode() == null || requestDTO.getZipCode().isEmpty()) {
            throw new IllegalArgumentException("Venue zip code cannot be null or empty");
        }
        log.info("Updating venue with ID: {}", id);

        VenueEntity venue = venueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venue not found with ID: " + id));

        venueMapper.updateEntityFromDTO(requestDTO, venue);
        VenueEntity updatedVenue = venueRepository.save(venue);

        log.info("Venue updated successfully with ID: {}", id);
        return venueMapper.toResponseDTO(updatedVenue);
    }

    @Transactional
    public void deleteVenue(Long id) {
        if (!venueRepository.existsById(id)) {
            throw new RuntimeException("Venue not found with ID: " + id);
        }
        log.info("Deleting venue with ID: {}", id);

        venueRepository.deleteById(id);
        log.info("Venue deleted successfully with ID: {}", id);
    }
}