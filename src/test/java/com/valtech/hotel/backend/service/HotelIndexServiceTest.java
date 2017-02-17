package com.valtech.hotel.backend.service;

import com.valtech.hotel.backend.entity.Hotel;
import com.valtech.hotel.backend.entity.rest.RestHotel;
import com.valtech.hotel.backend.repository.HotelIndexRepository;
import com.valtech.hotel.backend.service.rest.LoadHotelsService;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.Collections;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HotelIndexServiceTest {

    @Test
    public void createIndexAndPopulateData_verifyCalls() throws Exception {
        HotelIndexRepository hotelIndexRepository = mock(HotelIndexRepository.class);
        LoadHotelsService loadHotelsService = mock(LoadHotelsService.class);
        when(loadHotelsService.getHotelsFromSource(anyInt())).thenReturn(Collections.singletonList(new RestHotel()));

        HotelIndexService hotelIndexService = new HotelIndexService(hotelIndexRepository, loadHotelsService);
        hotelIndexService.createIndexAndPopulateData();

        InOrder inOrder = inOrder(hotelIndexRepository, loadHotelsService);
        inOrder.verify(hotelIndexRepository).deleteIndex();
        inOrder.verify(hotelIndexRepository).createIndex();
        inOrder.verify(loadHotelsService).getHotelsFromSource(HotelIndexService.AMOUNT_OF_ENTITIES);
        inOrder.verify(hotelIndexRepository).addHotels(anyListOf(Hotel.class));
    }
}