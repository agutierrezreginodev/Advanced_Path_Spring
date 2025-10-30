package com.codeup.eventify.repository;

import com.codeup.eventify.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {
    List<EventEntity> findByTitle(String title);

    List<EventEntity> findByVenueId(Long venueId);

    List<EventEntity> findByHostedBy(String hostedBy);

    boolean existsByTitle(String title);
}