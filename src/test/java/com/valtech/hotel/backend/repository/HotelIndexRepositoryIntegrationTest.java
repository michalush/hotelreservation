package com.valtech.hotel.backend.repository;

import com.tngtech.jgiven.annotation.JGivenConfiguration;
import com.tngtech.jgiven.annotation.ScenarioStage;
import com.tngtech.jgiven.integration.spring.SimpleSpringRuleScenarioTest;
import com.valtech.hotel.backend.repository.stage.CreateHotelStage;
import com.valtech.hotel.backend.repository.stage.HotelIndexStage;
import com.valtech.hotel.spring.HotelTestApplicationConfiguration;
import com.valtech.hotel.spring.HotelTestApplicationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@JGivenConfiguration(HotelTestApplicationConfiguration.class)
@SpringBootTest(classes = { HotelTestApplicationContext.class } )
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@ActiveProfiles("crudtest")
public class HotelIndexRepositoryIntegrationTest extends SimpleSpringRuleScenarioTest<HotelIndexStage> {

    @Autowired
    private HotelIndexRepository hotelRepository;

    @ScenarioStage
    private CreateHotelStage createHotelStage;

    @Before
    public void setUp() throws Exception {
        hotelRepository.createIndex();
    }

    @Test
    public void addHotelToTheIndex() throws Exception {
        createHotelStage.createHotel(1, "Motel One", "Perfect for business trips!", 12);

        given().there_is_no_hotel_with_id("1").
        when().new_hotel_is_added().then().hotel_with_id_$1_exists("1");
    }

    @Test
    public void bulkAddHotelsToTheIndex() throws Exception {
        createHotelStage.createHotel(1, "Motel One", "Perfect for business trips!", 12)
                .and().createHotel(2, "My Best Hotel", "Family and leisure", 10)
                .and().createHotel(5, "Anyday", "", 5);

        when().all_hotesl_are_added().then().hotel_with_id_$1_exists("1")
                .and().hotel_with_id_$1_exists("2")
                .and().hotel_with_id_$1_exists("5");
    }

    @After
    public void tearDown() throws Exception {
        hotelRepository.deleteIndex();
    }
}