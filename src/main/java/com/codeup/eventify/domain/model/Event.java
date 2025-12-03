package com.codeup.eventify.domain.model;

import java.time.LocalTime;
import java.util.Objects;

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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String title;
        private String description;
        private String date;
        private Venue venue;
        private LocalTime hour;
        private Double price;
        private String hostedBy;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder date(String date) {
            this.date = date;
            return this;
        }

        public Builder venue(Venue venue) {
            this.venue = venue;
            return this;
        }

        public Builder hour(LocalTime hour) {
            this.hour = hour;
            return this;
        }

        public Builder price(Double price) {
            this.price = price;
            return this;
        }

        public Builder hostedBy(String hostedBy) {
            this.hostedBy = hostedBy;
            return this;
        }

        public Event build() {
            return new Event(id, title, description, date, venue, hour, price, hostedBy);
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) &&
                Objects.equals(title, event.title) &&
                Objects.equals(description, event.description) &&
                Objects.equals(date, event.date) &&
                Objects.equals(venue, event.venue) &&
                Objects.equals(hour, event.hour) &&
                Objects.equals(price, event.price) &&
                Objects.equals(hostedBy, event.hostedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, date, venue, hour, price, hostedBy);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", venue=" + venue +
                ", hour=" + hour +
                ", price=" + price +
                ", hostedBy='" + hostedBy + '\'' +
                '}';
    }
}
