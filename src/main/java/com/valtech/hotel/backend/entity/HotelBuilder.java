package com.valtech.hotel.backend.entity;

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

    public Hotel build() {
        return hotel;
    }
}
