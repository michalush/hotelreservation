package com.valtech.hotel.backend.repository.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import com.valtech.hotel.backend.entity.Hotel;
import com.valtech.hotel.backend.repository.HotelIndexRepository;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@JGivenStage
public class HotelIndexStage extends Stage<HotelIndexStage> {
    @Autowired
    private HotelIndexRepository hotelRepository;
    @Autowired
    private Client elasticsearchClient;

    public HotelIndexStage new_hotel_is_added(Hotel hotel) {
        hotelRepository.add(hotel);
        return this;
    }

    public HotelIndexStage hotel_with_id_$1_exists(String id) {
        final GetResponse response = getHotelWithId(id);
        assertThat(response.isExists(), is(true));
        return this;
    }

    private GetResponse getHotelWithId(String id) {
        return elasticsearchClient.prepareGet(HotelIndexRepository.HOTEL, HotelIndexRepository.TYPE, id).get();
    }

    public HotelIndexStage there_is_no_hotel_with_id(String id) {
        final GetResponse response = getHotelWithId(id);
        assertThat(response.isExists(), is(false));
        return this;
    }
}
