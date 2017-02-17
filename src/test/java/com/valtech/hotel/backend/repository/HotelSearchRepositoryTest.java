package com.valtech.hotel.backend.repository;

import com.tngtech.jgiven.annotation.AfterScenario;
import com.tngtech.jgiven.annotation.BeforeScenario;
import com.tngtech.jgiven.annotation.JGivenConfiguration;
import com.tngtech.jgiven.integration.spring.SpringRuleScenarioTest;
import com.valtech.hotel.backend.repository.stage.GivenHotelStage;
import com.valtech.hotel.backend.repository.stage.ThenHotelFound;
import com.valtech.hotel.backend.repository.stage.WhenSearchForHotel;
import com.valtech.hotel.spring.HotelTestApplicationConfiguration;
import com.valtech.hotel.spring.HotelTestApplicationContext;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@JGivenConfiguration(HotelTestApplicationConfiguration.class)
@SpringBootTest(classes = { HotelTestApplicationContext.class } )
@ActiveProfiles("local")
public class HotelSearchRepositoryTest extends SpringRuleScenarioTest<GivenHotelStage, WhenSearchForHotel, ThenHotelFound> {
    @Autowired
    private HotelIndexRepository hotelRepository;

    @BeforeScenario
    public void setUp() throws Exception {
        hotelRepository.createIndex();
    }

    @Test
    public void search_in_description() throws Exception {
        given().business_hotel_exists().and().family_hotel_exists();
        when().search_for_key_word_business();
        then().only_hotel_with_keyword_business_in_the_description_is_found();
    }

    @Test
    public void search_in_name() throws Exception {
        given().business_hotel_exists().and().family_hotel_exists();
        when().search_for_name_of_family_hotel();
        then().only_hotel_with_that_name_is_found();
    }

    @Test
    public void search_by_empty_string() throws Exception {
        given().business_hotel_exists().and().family_hotel_exists();
        when().search_for_empty_string();
        then().both_hotels_are_found();
    }

    @Test
    public void search_and_sort() throws Exception {
        given().business_hotel_exists().and().family_hotel_exists();
        when().search_for_empty_string();
        then().both_hotels_are_found().and()
                .they_are_ordered_by_rating();
    }

    @AfterScenario
    public void tearDown() throws Exception {
        hotelRepository.deleteIndex();
    }
}