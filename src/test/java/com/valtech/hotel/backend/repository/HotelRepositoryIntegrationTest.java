package com.valtech.hotel.backend.repository;

import com.tngtech.jgiven.annotation.JGivenConfiguration;
import com.tngtech.jgiven.integration.spring.SimpleSpringRuleScenarioTest;
import com.valtech.hotel.backend.entity.Hotel;
import com.valtech.hotel.spring.HotelTestApplicationConfiguration;
import com.valtech.hotel.spring.HotelTestApplicationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@JGivenConfiguration(HotelTestApplicationConfiguration.class)
@SpringBootTest(classes = { HotelTestApplicationContext.class } )
@ActiveProfiles("local")
public class HotelRepositoryIntegrationTest extends SimpleSpringRuleScenarioTest<HotelSearchRepoStage> {

    @Autowired
    private HotelRepository hotelRepository;

    @Before
    public void setUp() throws Exception {
        hotelRepository.createIndex();
    }

    @Test
    public void addHotelToRepository() throws Exception {
        final Hotel hotel = new Hotel();
        hotel.setId("1");
        hotel.setName("Motel One");
        hotel.setDescription("Perfect for business trips!");

        given().there_is_no_hotel_with_id("1").
        when().new_hotel_is_added(hotel).then().hotel_with_id_$1_exists("1");
    }

    @After
    public void tearDown() throws Exception {
        hotelRepository.deleteIndex();
    }
}