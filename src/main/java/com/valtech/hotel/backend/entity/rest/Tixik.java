package com.valtech.hotel.backend.entity.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Represents xml entity of www.tixik.com/api/
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "tixik")
public class Tixik implements Serializable {
    private RestHotelList restHotelList;

    @XmlElement(name="items")
    public RestHotelList getRestHotelList() {
        return restHotelList;
    }

    public void setRestHotelList(RestHotelList restHotelList) {
        this.restHotelList = restHotelList;
    }
}
