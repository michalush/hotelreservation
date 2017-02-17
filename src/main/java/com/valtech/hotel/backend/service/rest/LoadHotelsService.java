package com.valtech.hotel.backend.service.rest;

import com.valtech.hotel.backend.entity.rest.RestHotel;
import com.valtech.hotel.backend.entity.rest.Tixik;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.util.Collections;
import java.util.List;

@Service
public class LoadHotelsService {
    private static final Logger LOG = LoggerFactory.getLogger(LoadHotelsService.class);

    private final RestTemplate restTemplate;

    public LoadHotelsService(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<RestHotel> getHotelsFromSource(final int amountOfEntities) {
        final String url = "http://www.tixik.com/api/nearby?lat=36.106121163930377&lng=28.07762145996093&limit="
                + amountOfEntities + "&key=demo";
        final StreamSource source = restTemplate.getForObject(url, StreamSource.class);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Tixik.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Tixik restHotel = (Tixik) jaxbUnmarshaller.unmarshal(source);
            final List<RestHotel> restHotels = restHotel.getRestHotelList().getRestHotels();

            return restHotels;

        } catch (JAXBException e) {
            LOG.error("Failed to parse response from {}", url, e);
        }

        return Collections.emptyList();
    }

}
