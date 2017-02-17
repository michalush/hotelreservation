package com.valtech.hotel.backend.service.rest;

import com.valtech.hotel.backend.entity.rest.RestHotel;
import com.valtech.hotel.spring.HotelTestApplicationContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { HotelTestApplicationContext.class })
@ActiveProfiles("local")
public class LoadHotelsServiceTest {
    @Autowired
    private LoadHotelsService hotelsService;

    @Test
    public void test() throws Exception {
        final int amountOfEntities = 10;
        final List<RestHotel> hotelsFromSource = hotelsService.getHotelsFromSource(amountOfEntities);

        assertThat(hotelsFromSource.size(), is(amountOfEntities));
    }
}