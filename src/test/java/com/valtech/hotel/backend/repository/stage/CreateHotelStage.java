package com.valtech.hotel.backend.repository.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.valtech.hotel.backend.entity.Hotel;
import com.valtech.hotel.backend.entity.HotelBuilder;

public class CreateHotelStage extends Stage<CreateHotelStage> {
    @ProvidedScenarioState
    private Hotel hotel;

    @As("Create hotel with id: $1, name: '$2', description: '$3' and rating: $4")
    public CreateHotelStage createHotel(int id,
                             String name,
                             String description,
                             int rating) {
        hotel = HotelBuilder.createHotel(id)
                .withDetails(name, description).withRating(rating)
                .build();
        return this;
    }
}
