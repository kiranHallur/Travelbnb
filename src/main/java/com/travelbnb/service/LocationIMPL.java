package com.travelbnb.service;

import com.travelbnb.entity.Location;
import com.travelbnb.exception.ResourceNotFound;
import com.travelbnb.repository.CountryRepository;
import com.travelbnb.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationIMPL implements LocationService{
    private LocationRepository locationRepository;

    public LocationIMPL(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location addLocation(Location location){
        Location location1=new Location();
           location1.setName(location.getName());
        Location save = locationRepository.save(location);
        return save;
    }

    @Override
    public void deleteLocation(long locationId) {
        locationRepository.deleteById(locationId);
    }

    @Override
    public Location updateLocation(long locationId, Location location) {
        Optional<Location> byId = locationRepository.findById(locationId);

        if (byId.isPresent()) {
             Location location1 = byId.get();
            location1.setName(location.getName());
            Location save = locationRepository.save(location);
            return save;

        } else {
            throw new ResourceNotFound("LocationId Not Found: "+locationId);
        }

    }

    @Override
    public List<Location> getAll() {
        return locationRepository.findAll();

    }

    @Override
    public Location getById(long locationId) {
        Optional<Location> byId = locationRepository.findById(locationId);

        if(byId.isPresent()){
            Location location = byId.get();
            return location;
        }else{
            throw new ResourceNotFound("LocationId Not FoundId: "+locationId);
        }

    }
}
