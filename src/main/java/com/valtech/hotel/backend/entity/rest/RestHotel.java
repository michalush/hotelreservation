package com.valtech.hotel.backend.entity.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "item")
public class RestHotel implements Serializable {

    private int id;
    private String name;
    private String description;
    private Double gps_x;
    private Double gps_y;

    public int getId() {
        return id;
    }

    @XmlElement(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    @XmlElement(name = "description")
    public void setDescription(String description) {
        this.description = description;
    }

    public Double getGps_x() {
        return gps_x;
    }

    @XmlElement(name = "gps_x")
    public void setGps_x(Double gps_x) {
        this.gps_x = gps_x;
    }

    public Double getGps_y() {
        return gps_y;
    }

    @XmlElement(name = "gps_y")
    public void setGps_y(Double gps_y) {
        this.gps_y = gps_y;
    }
}
