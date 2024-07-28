package com.travelbnb.service;

import com.travelbnb.entity.Country;

import java.util.List;

public interface CountryService {
    public Country addCountry(Country country);
    public void deleteCountry(long countryId);
    public Country updateCountry(long countryId,Country country);
    public List<Country>getAll();
    public Country getById(long countryId);

}
