package com.valtech.hotel.backend.repository.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.annotation.ScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import com.valtech.hotel.backend.entity.Hotel;
import org.hamcrest.Matcher;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@JGivenStage
public class ThenHotelFound extends Stage<ThenHotelFound> {
    @ProvidedScenarioState(resolution = ScenarioState.Resolution.NAME)
    private Hotel businessHotel;
    @ProvidedScenarioState(resolution = ScenarioState.Resolution.NAME)
    private Hotel familyHotel;
    @ProvidedScenarioState
    private List<Hotel> searchResult;

    public ThenHotelFound only_hotel_with_keyword_business_in_the_description_is_found() {
        assertListSize(is(1));
        assertHotelFound(businessHotel);

        return this;
    }

    public ThenHotelFound only_hotel_with_that_name_is_found() {
        assertListSize(is(1));
        assertHotelFound(familyHotel);

        return this;
    }

    public ThenHotelFound both_hotels_are_found() {
        assertListSize(is(2));
        assertHotelFound(familyHotel);
        assertHotelFound(businessHotel);

        return this;
    }

    private void assertHotelFound(Hotel hotel) {
        assertThat(searchResult.contains(hotel), is(true));
    }

    private void assertListSize(Matcher<Integer> matcher) {
        assertThat(searchResult.size(), matcher);
    }
}
