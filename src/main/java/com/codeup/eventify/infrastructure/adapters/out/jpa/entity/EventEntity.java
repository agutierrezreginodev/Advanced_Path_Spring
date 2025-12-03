package com.codeup.eventify.infrastructure.adapters.out.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Data
@Builder
@Entity
@Table(name = "events")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "venue_id", referencedColumnName = "id")
    private VenueEntity venue;

    @Column(name = "date")
    private String date;

    @Column(name = "event_hour")
    private LocalTime hour;

    @Column(name = "price")
    private Double price;

    @Column(name = "hoster")
    private String hostedBy;
}