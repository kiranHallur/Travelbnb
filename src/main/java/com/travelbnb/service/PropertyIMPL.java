package com.travelbnb.service;

import com.travelbnb.dto.PropertyDto;
import com.travelbnb.entity.Country;
import com.travelbnb.entity.Location;
import com.travelbnb.entity.Property;
import com.travelbnb.exception.ResourceNotFound;
import com.travelbnb.repository.CountryRepository;
import com.travelbnb.repository.LocationRepository;
import com.travelbnb.repository.PropertyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyIMPL implements PropertyService{


    private PropertyRepository propertyRepository;
    private CountryRepository countryRepository;
    private LocationRepository locationRepository;

    public PropertyIMPL(PropertyRepository propertyRepository, CountryRepository countryRepository, LocationRepository locationRepository) {
        this.propertyRepository = propertyRepository;
        this.countryRepository = countryRepository;
        this.locationRepository = locationRepository;
    }
    public Property dtoToEntity(PropertyDto dto){
        Property property=new Property();
        property.setName(dto.getName());
        property.setCountry(dto.getCountry());
        property.setLocation(dto.getLocation());
        property.setPrice(dto.getPrice());
        property.setNoBathrooms(dto.getNoBathrooms());
        property.setNoBedrooms(dto.getNoBedrooms());
        property.setNoGuests(dto.getNoGuests());
        return property;
    }
    public PropertyDto entityToDto (Property property){
        PropertyDto propertyDto=new PropertyDto();
        propertyDto.setId(property.getId());
        propertyDto.setName(property.getName());
        propertyDto.setCountry(property.getCountry());
        propertyDto.setLocation(property.getLocation());
        propertyDto.setPrice(property.getPrice());
        propertyDto.setNoBathrooms(property.getNoBathrooms());
        propertyDto.setNoBedrooms(property.getNoBedrooms());
        propertyDto.setNoGuests(property.getNoGuests());
        return propertyDto;
    }

    @Override
    public PropertyDto addProperty( long countryId,long locationId,Property property) {
        Country country = countryRepository.findById(countryId).get();
        property.setCountry(country);
        Location location = locationRepository.findById(locationId).get();
        property.setLocation(location);
        Property save = propertyRepository.save(property);
        PropertyDto propertyDto = entityToDto(save);
        return propertyDto;
    }

    @Override
    public void deleteProperty(long propertyId) {
      propertyRepository.deleteById(propertyId);
    }

    @Override
    public PropertyDto updateProperty(long propertyId,long countryId,long locationId, PropertyDto dto) {
        Optional<Property> byId = propertyRepository.findById(propertyId);
        Property property=null;
        if(byId.isPresent()){
              property = byId.get();
            property.setName(dto.getName());
            property.setCountry(dto.getCountry());
            property.setLocation(dto.getLocation());
            property.setPrice(dto.getPrice());
            property.setNoBathrooms(dto.getNoBathrooms());
            property.setNoBedrooms(dto.getNoBedrooms());
            property.setNoGuests(dto.getNoGuests());
            Country country = countryRepository.findById(countryId).get();
            property.setCountry(country);
            Location location = locationRepository.findById(locationId).get();
            property.setLocation(location);
            Property save = propertyRepository.save(property);
            PropertyDto propertyDto = entityToDto(save);
            return propertyDto;
        }
        else{
            throw new ResourceNotFound("PropertyId Not Found: "+propertyId);
        }
    }

    @Override
    public List<PropertyDto> getAllProperty(int pageSize, int pageNo, String sortBy,String name, String sortDir) {
        Pageable pageable=null;
        if(sortDir.equalsIgnoreCase("asc")){
            pageable=PageRequest.of(pageSize, pageNo,Sort.by(sortBy).ascending());
        }if(sortDir.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(pageSize, pageNo, Sort.by(sortBy).descending());
        }
        Page<Property> all = propertyRepository.findAll(pageable);
        List<Property> content = all.getContent();

        List<PropertyDto> collect = content.stream().map(c -> entityToDto(c)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public Property getById(long propertyId) {
        Optional<Property> byId = propertyRepository.findById(propertyId);
        if(byId.isPresent()){
            Property property = byId.get();
            return property;
        }
         else {
             throw new ResourceNotFound("Property Not Found Id: "+propertyId);
        }
    }
    @Override
    public List<Property> searchProperty(String name) {
        List<Property> Properties  = propertyRepository.searchProperty(name);
        return Properties;
    }

}
