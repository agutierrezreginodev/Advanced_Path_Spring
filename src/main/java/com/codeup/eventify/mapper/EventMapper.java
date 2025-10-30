package com.codeup.eventify.mapper;

import com.codeup.eventify.web.dto.request.EventRequestDTO;
import com.codeup.eventify.web.dto.response.EventResponseDTO;
import com.codeup.eventify.entity.EventEntity;
import com.codeup.eventify.entity.VenueEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventMapper {

    private final VenueMapper venueMapper;

    public EventEntity toEntity(EventRequestDTO dto, VenueEntity venue) {
        if (dto == null) {
            return null;
        }

        return EventEntity.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .venue(venue)
                .date(dto.getDate())
                .hour((dto.getHour()))
                .price(dto.getPrice())
                .hostedBy(dto.getHostedBy())
                .build();
    }

    public EventResponseDTO toResponseDTO(EventEntity entity) {
        if (entity == null) {
            return null;
        }

        return EventResponseDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .venue(venueMapper.toResponseDTO(entity.getVenue()))
                .date(entity.getDate())
                .hour(entity.getHour())
                .price(entity.getPrice())
                .hostedBy(entity.getHostedBy())
                .build();
    }

    public void updateEntityFromDTO(EventRequestDTO dto, EventEntity entity, VenueEntity venue) {
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setVenue(venue);
        entity.setDate(dto.getDate());
        entity.setHour(dto.getHour());
        entity.setPrice(dto.getPrice());
        entity.setHostedBy(dto.getHostedBy());
    }
}