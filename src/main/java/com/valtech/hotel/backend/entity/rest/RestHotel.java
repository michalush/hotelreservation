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
}
