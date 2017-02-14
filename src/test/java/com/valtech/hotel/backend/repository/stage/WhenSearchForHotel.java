package com.valtech.hotel.backend.repository.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.annotation.ScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import com.valtech.hotel.backend.entity.Hotel;
import com.valtech.hotel.backend.repository.HotelSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@JGivenStage
public class WhenSearchForHotel extends Stage<WhenSearchForHotel> {
    @Autowired
    private HotelSearchRepository hotelRepository;

    @ProvidedScenarioState(resolution = ScenarioState.Resolution.NAME)
    private Hotel businessHotel;
    @ProvidedScenarioState(resolution = ScenarioState.Resolution.NAME)
    private Hotel familyHotel;
    @ProvidedScenarioState
    private List<Hotel> searchResult;

    public WhenSearchForHotel search_for_key_word_business() {
        searchResult = hotelRepository.findHotel("business");

        return this;
    }

}
