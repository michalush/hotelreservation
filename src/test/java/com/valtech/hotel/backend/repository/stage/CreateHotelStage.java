package com.valtech.hotel.backend.repository.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.valtech.hotel.backend.entity.Hotel;
import com.valtech.hotel.backend.entity.HotelBuilder;

import java.util.ArrayList;
import java.util.List;

public class CreateHotelStage extends Stage<CreateHotelStage> {
    @ProvidedScenarioState
    private List<Hotel> hotels = new ArrayList<Hotel>();

    @As("Create hotel with id: $1, name: '$2', description: '$3' and rating: $4")
    public CreateHotelStage createHotel(int id,
                             String name,
                             String description,
                             int rating) {
        hotels.add(HotelBuilder.createHotel(id)
                .withDetails(name, description).withRating(rating)
                .build());
        return this;
    }
}
