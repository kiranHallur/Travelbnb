package com.travelbnb.controller;


import com.travelbnb.entity.Location;
import com.travelbnb.service.LocationIMPL;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/location")
public class LocationController {
    private LocationIMPL locationIMPL;

    public LocationController(LocationIMPL locationIMPL) {
        this.locationIMPL = locationIMPL;
    }
    @PostMapping("/addLocation")
    public ResponseEntity<Location>addLocation(@RequestBody Location location){
        Location location1 = locationIMPL.addLocation(location);
        return new ResponseEntity<>(location1, HttpStatus.CREATED);
    }
    @DeleteMapping
    public ResponseEntity<String>deleteLocation(@RequestParam long locationId){
        locationIMPL.deleteLocation(locationId);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }
    @PutMapping("/{locationId}")
    public ResponseEntity<Location>updateLocation(@PathVariable long locationId,@RequestBody Location location){
        Location location1 = locationIMPL.updateLocation(locationId, location);
        return new ResponseEntity<>(location1,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Location>>getAllLocation(){
        List<Location> all = locationIMPL.getAll();
        return new ResponseEntity<>(all,HttpStatus.OK);
    }
    @GetMapping("/{locationId}")
    public ResponseEntity<Location>getById(@RequestParam long locationId){
        Location byId = locationIMPL.getById(locationId);
        return new ResponseEntity<>(byId,HttpStatus.OK);
    }
}
