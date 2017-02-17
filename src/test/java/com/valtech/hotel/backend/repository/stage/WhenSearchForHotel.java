package com.valtech.hotel.backend.repository.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
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

    @ExpectedScenarioState(resolution = ScenarioState.Resolution.NAME)
    private Hotel businessHotel;
    @ExpectedScenarioState(resolution = ScenarioState.Resolution.NAME)
    private Hotel familyHotel;
    @ProvidedScenarioState
    private List<Hotel> searchResult;

    public WhenSearchForHotel search_for_key_word_business() {
        searchResult = findHotel("business");

        return this;
    }

    public WhenSearchForHotel search_for_name_of_family_hotel() {
        searchResult = findHotel(familyHotel.getName());

        return this;
    }

    public WhenSearchForHotel search_for_empty_string() {
        searchResult = findHotel("");

        return this;
    }

    private List<Hotel> findHotel(String searchText) {
        return hotelRepository.findHotel(searchText);
    }
}
