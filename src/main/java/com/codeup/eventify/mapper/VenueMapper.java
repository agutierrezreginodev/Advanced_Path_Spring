package com.codeup.eventify.mapper;

import com.codeup.eventify.web.dto.request.VenueRequestDTO;
import com.codeup.eventify.web.dto.response.VenueResponseDTO;
import com.codeup.eventify.entity.VenueEntity;
import org.springframework.stereotype.Component;

@Component
public class VenueMapper {

    public VenueEntity toEntity(VenueRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        return VenueEntity.builder()
                .name(dto.getName())
                .country(dto.getCountry())
                .address(dto.getAddress())
                .city(dto.getCity())
                .state(dto.getState())
                .zipCode(dto.getZipCode())
                .build();
    }

    public VenueResponseDTO toResponseDTO(VenueEntity entity) {
        if (entity == null) {
            return null;
        }

        return VenueResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .country(entity.getCountry())
                .address(entity.getAddress())
                .city(entity.getCity())
                .state(entity.getState())
                .zipCode(entity.getZipCode())
                .build();
    }

    public void updateEntityFromDTO(VenueRequestDTO dto, VenueEntity entity) {
        entity.setName(dto.getName());
        entity.setCountry(dto.getCountry());
        entity.setAddress(dto.getAddress());
        entity.setCity(dto.getCity());
        entity.setState(dto.getState());
        entity.setZipCode(dto.getZipCode());
    }
}
