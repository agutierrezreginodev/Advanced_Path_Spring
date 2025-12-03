package com.codeup.eventify.infrastructure.adapters.out.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "venues")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VenueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "venue_name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip_code")
    private String zipCode;
}