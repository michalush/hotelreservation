package com.valtech.hotel.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Hotel implements Serializable {
    public static final String TYPE = "hotel";

    private Integer id;
    private String name;
    private String description;
    private int rating;
    private Location location;

    public Hotel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Hotel)) return false;

        Hotel hotel = (Hotel) o;

        return new EqualsBuilder()
                .append(id, hotel.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }

    public void setLocationDetails(Double gps_x, Double gps_y) {
        setLocation(new Location(gps_x, gps_y));
    }

    public static class Location implements Serializable {

        private Double lat;
        private Double lon;

        public Location() {
        }

        public Location(Double lat, Double lon) {

            this.lat = lat;
            this.lon = lon;
        }

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLon() {
            return lon;
        }

        public void setLon(Double lon) {
            this.lon = lon;
        }

        @JsonIgnore
        public String getGeohash() {

            return new org.elasticsearch.common.geo.GeoPoint(this.lat, this.lon).getGeohash();
        }

    }
}
