package com.valtech.hotel.backend.service;

import com.valtech.hotel.backend.entity.Hotel;
import com.valtech.hotel.backend.entity.HotelBuilder;
import com.valtech.hotel.backend.entity.rest.RestHotel;
import com.valtech.hotel.backend.repository.HotelIndexRepository;
import com.valtech.hotel.backend.service.rest.LoadHotelsService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelIndexService {

    static final int AMOUNT_OF_ENTITIES = 10000;
    private static final int MIN = 0;
    private static final int MAX = 100;

    private final HotelIndexRepository hotelIndexRepository;
    private final LoadHotelsService loadHotelsService;

    public HotelIndexService(@Autowired HotelIndexRepository hotelIndexRepository,
                             @Autowired LoadHotelsService loadHotelsService) {
        this.hotelIndexRepository = hotelIndexRepository;
        this.loadHotelsService = loadHotelsService;
    }

    public void createIndexAndPopulateData() {
        hotelIndexRepository.deleteIndex();
        hotelIndexRepository.createIndex();
        final List<RestHotel> hotelsFromSource = loadHotelsService.getHotelsFromSource(AMOUNT_OF_ENTITIES);
        if (CollectionUtils.isNotEmpty(hotelsFromSource)) {
            final List<Hotel> hotels = hotelsFromSource.stream().map(restHotel -> transformHotel(restHotel))
                    .collect(Collectors.toList());
            hotelIndexRepository.addHotels(hotels);
        }
    }

    private Hotel transformHotel(RestHotel restHotel) {
        return HotelBuilder.createHotel(restHotel.getId())
                .withDetails(restHotel.getName(), restHotel.getDescription())
                .withLocation(restHotel.getGps_x(), restHotel.getGps_y())
                .withRating(createRandomValue()).build();
    }

    private int createRandomValue() {
       return MIN + (int)(Math.random() * ((MAX - MIN) + 1));
    }
}
