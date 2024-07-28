package com.travelbnb.service;



import com.travelbnb.entity.Country;
import com.travelbnb.exception.ResourceNotFound;
import com.travelbnb.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryIMPL implements CountryService {


    private CountryRepository countryRepository;

    public CountryIMPL(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }
    public Country addCountry(Country country){
        Country country1=new Country();
        country1.setName(country.getName());
        Country save = countryRepository.save(country);
        return save;
    }

    @Override
    public void deleteCountry(long countryId) {
        countryRepository.deleteById(countryId);
    }

    @Override
    public Country updateCountry(long countryId, Country country) {
        Optional<Country> byId = countryRepository.findById(countryId);

        if(byId.isPresent()){
            Country country1 = byId.get();
            country1.setName(country.getName());
            return countryRepository.save(country1);

        }else{
            throw new ResourceNotFound("CountryId Not Found: "+countryId);
        }

    }

    @Override
    public List<Country> getAll() {
        return countryRepository.findAll();

    }
    @Override
    public Country getById(long countryId) {
        Country country = countryRepository.findById(countryId).orElseThrow(
                ()->new ResourceNotFound("CountryId Not Found: "+countryId)
        );
        return country;
    }
}
