package com.travelbnb.service;

import com.travelbnb.entity.Location;

import java.util.List;

public interface LocationService {
    public Location addLocation(Location location);
    public void deleteLocation(long locationId);
    public Location updateLocation(long locationId,Location location);
    public List<Location>getAll();
    public Location getById(long locationId);
}
