package com.travelbnb.service;

import com.travelbnb.dto.PropertyDto;
import com.travelbnb.entity.Property;

import java.util.List;

public interface PropertyService {
    public PropertyDto addProperty(long countryId,long locationId,Property property);
    public void deleteProperty(long propertyId);
    public PropertyDto updateProperty(long propertyId,long countryId,long locationId,PropertyDto dto);
    public List<PropertyDto>getAllProperty(int pageSize,int pageNo,String sortBy,String name,String sortDir);
    public Property getById(long propertyId);
    public List<Property>searchProperty(String name);
}
