package com.valtech.hotel.backend.repository;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import com.valtech.hotel.backend.entity.Hotel;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@JGivenStage
public class HotelSearchRepoStage extends Stage<HotelSearchRepoStage> {
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private Client elasticsearchClient;

    public HotelSearchRepoStage createHotel(Hotel hotel) {
        hotelRepository.add(hotel);
        return this;
    }

    public HotelSearchRepoStage hotelExists(String id) {
        final GetResponse response = elasticsearchClient.prepareGet(HotelRepository.HOTEL, HotelRepository.TYPE, id).get();
        assertThat(response.isExists(), is(true));
        return this;
    }

    public HotelSearchRepoStage hotelIsNotPresentWithId(String id) {
        final GetResponse response = elasticsearchClient.prepareGet(HotelRepository.HOTEL, HotelRepository.TYPE, id).get();
        assertThat(response.isExists(), is(false));
        return this;
    }
}
