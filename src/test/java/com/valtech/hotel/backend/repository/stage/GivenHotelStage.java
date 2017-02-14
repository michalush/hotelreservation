package com.valtech.hotel.backend.repository.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.annotation.ScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import com.valtech.hotel.backend.entity.Hotel;
import com.valtech.hotel.backend.entity.HotelBuilder;
import com.valtech.hotel.backend.repository.HotelIndexRepository;
import org.springframework.beans.factory.annotation.Autowired;

@JGivenStage
public class GivenHotelStage extends Stage<GivenHotelStage> {
    @Autowired
    private HotelIndexRepository hotelRepository;

    @ProvidedScenarioState(resolution = ScenarioState.Resolution.NAME)
    private Hotel businessHotel;
    @ProvidedScenarioState(resolution = ScenarioState.Resolution.NAME)
    private Hotel familyHotel;

    public GivenHotelStage business_hotel_exists() {
        businessHotel = HotelBuilder.createHotel(1)
                .withDetails("Motel One", "Perfect for business trip!")
                .withRating(3)
                .build();
        hotelRepository.add(businessHotel);
        return this;
    }

    public GivenHotelStage family_hotel_exists() {
        familyHotel = HotelBuilder.createHotel(2)
                .withDetails("Pansion Wilkommen", "Joy for the whole family")
                .withRating(5)
                .build();
        hotelRepository.add(familyHotel);
        return this;
    }
}
