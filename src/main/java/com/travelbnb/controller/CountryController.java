package com.travelbnb.controller;
import com.travelbnb.entity.Country;
import com.travelbnb.service.CountryIMPL;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {

    private CountryIMPL countryIMPL;

    public CountryController(CountryIMPL countryIMPL) {
        this.countryIMPL = countryIMPL;
    }
    @PostMapping("/createCountry")
    public ResponseEntity<Country>addCountry(@RequestBody Country country){
        Country country1 = countryIMPL.addCountry(country);
        return new ResponseEntity<>(country1, HttpStatus.CREATED);
    }
    @DeleteMapping
    public ResponseEntity<String>deleteCountry(@RequestParam long countryId){
        countryIMPL.deleteCountry(countryId);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }
    @PutMapping("/{countryId}")
    public ResponseEntity<Country>updateCountry(
            @PathVariable long countryId,@RequestBody Country country){
        Country country1 = countryIMPL.updateCountry(countryId, country);
        return new ResponseEntity<>(country1,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Country>>getAllCountry(){
        List<Country> all = countryIMPL.getAll();
        return new ResponseEntity<>(all,HttpStatus.OK);
    }
    @GetMapping("/{countryId}")
    public ResponseEntity<Country>getById(@RequestParam long countryId){
        Country byId = countryIMPL.getById(countryId);
        return new ResponseEntity<>(byId,HttpStatus.OK);
    }
}
