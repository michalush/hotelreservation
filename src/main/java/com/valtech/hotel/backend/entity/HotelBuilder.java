package com.valtech.hotel.backend.entity;

import org.elasticsearch.common.geo.GeoPoint;

public class HotelBuilder {
    private final Hotel hotel = new Hotel();

    private HotelBuilder(int id) {
        hotel.setId(id);
    }

    public static HotelBuilder createHotel(int id) {
        return new HotelBuilder(id);
    }

    public HotelBuilder withDetails(String name, String description) {
        hotel.setName(name);
        hotel.setDescription(description);

        return this;
    }

    public HotelBuilder withRating(int ratings) {
        hotel.setRating(ratings);

        return this;
    }

    public HotelBuilder withLocation(Double gps_x, Double gps_y) {
        if (gps_x != null || gps_y != null) {
            hotel.setLocation(new GeoPoint(gps_x, gps_y));
        }

        return this;
    }

    public Hotel build() {
        return hotel;
    }
}
