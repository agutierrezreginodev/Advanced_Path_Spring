package com.codeup.eventify.domain.model;

import java.util.Objects;

public class Venue {
    private Long id;
    private String name;
    private String country;
    private String address;
    private String city;
    private String state;
    private String zipCode;

    public Venue() {
    }

    public Venue(Long id, String name, String country, String address, String city, String state, String zipCode) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;
        private String country;
        private String address;
        private String city;
        private String state;
        private String zipCode;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder zipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Venue build() {
            return new Venue(id, name, country, address, city, state, zipCode);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Venue venue = (Venue) o;
        return Objects.equals(id, venue.id) &&
                Objects.equals(name, venue.name) &&
                Objects.equals(country, venue.country) &&
                Objects.equals(address, venue.address) &&
                Objects.equals(city, venue.city) &&
                Objects.equals(state, venue.state) &&
                Objects.equals(zipCode, venue.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country, address, city, state, zipCode);
    }

    @Override
    public String toString() {
        return "Venue{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
