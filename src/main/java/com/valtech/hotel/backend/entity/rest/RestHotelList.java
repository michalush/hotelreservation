package com.valtech.hotel.backend.entity.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "items")
public class RestHotelList implements Serializable {
    private List<RestHotel> restHotels;

    @XmlElement(name="item")
    public List<RestHotel> getRestHotels() {
        return restHotels;
    }

    public void setRestHotels(List<RestHotel> restHotels) {
        this.restHotels = restHotels;
    }
}
