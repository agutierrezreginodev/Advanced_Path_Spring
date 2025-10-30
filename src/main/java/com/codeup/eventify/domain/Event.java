package com.codeup.eventify.domain;

import java.time.LocalTime;

public class Event {
    private Long id;
    private String title;
    private String description;
    private String date;
    private Venue venue;
    private LocalTime hour;
    private Double price;
    private String hostedBy;

    public Event() {
    }

    public Event(Long id, String title, String description, String date, Venue venue, LocalTime hour, Double price,
            String hostedBy) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.venue = venue;
        this.hour = hour;
        this.price = price;
        this.hostedBy = hostedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getHostedBy() {
        return hostedBy;
    }

    public void setHostedBy(String hostedBy) {
        this.hostedBy = hostedBy;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }
}
