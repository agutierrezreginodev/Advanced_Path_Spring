package com.codeup.eventify.infrastructure.adapters.out.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeup.eventify.infrastructure.adapters.out.jpa.entity.EventEntity;

import java.util.List;

@Repository
public interface SpringEventRepository extends JpaRepository<EventEntity, Long> {
    List<EventEntity> findByTitle(String title);

    List<EventEntity> findByVenueId(Long venueId);

    List<EventEntity> findByHostedBy(String hostedBy);

    boolean existsByTitle(String title);
}
