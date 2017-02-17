package com.valtech.hotel.backend.repository.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import com.valtech.hotel.backend.entity.Hotel;
import com.valtech.hotel.backend.repository.HotelIndexRepository;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@JGivenStage
public class HotelIndexStage extends Stage<HotelIndexStage> {
    @Autowired
    private HotelIndexRepository hotelRepository;
    @Autowired
    private Client elasticsearchClient;
    @ExpectedScenarioState
    private List<Hotel> hotels;

    public HotelIndexStage new_hotel_is_added() {
        hotelRepository.add(hotels.get(0));
        return this;
    }

    public HotelIndexStage hotel_with_id_$1_exists(String id) {
        final GetResponse response = getHotelWithId(id);
        assertThat(response.isExists(), is(true));
        return this;
    }

    private GetResponse getHotelWithId(String id) {
        return hotelRepository.getHotelById(id);
    }

    public HotelIndexStage there_is_no_hotel_with_id(String id) {
        final GetResponse response = getHotelWithId(id);
        assertThat(response.isExists(), is(false));
        return this;
    }

    public HotelIndexStage all_hotesl_are_added() {
        hotelRepository.addHotels(hotels);

        return this;
    }
}
