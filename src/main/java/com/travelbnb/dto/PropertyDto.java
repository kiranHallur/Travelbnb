package com.travelbnb.dto;

import com.travelbnb.entity.Country;
import com.travelbnb.entity.Location;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class PropertyDto {
    private Long id;

    private String name;

    private Integer noGuests;

    private Integer noBedrooms;

    private Integer noBathrooms;

    private Integer price;

    private Country country;

    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getNoBathrooms() {
        return noBathrooms;
    }

    public void setNoBathrooms(Integer noBathrooms) {
        this.noBathrooms = noBathrooms;
    }

    public Integer getNoBedrooms() {
        return noBedrooms;
    }

    public void setNoBedrooms(Integer noBedrooms) {
        this.noBedrooms = noBedrooms;
    }

    public Integer getNoGuests() {
        return noGuests;
    }

    public void setNoGuests(Integer noGuests) {
        this.noGuests = noGuests;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
