package com.travelbnb.controller;
import com.travelbnb.dto.PropertyDto;
import com.travelbnb.entity.Property;
import com.travelbnb.service.PropertyIMPL;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {
    private PropertyIMPL propertyIMPL;

    public PropertyController(PropertyIMPL propertyIMPL) {
        this.propertyIMPL = propertyIMPL;
    }
    @PostMapping("/addProperty")
    public ResponseEntity<PropertyDto>addProperty(
            @RequestParam long countryId, @RequestParam long locationId,@RequestBody Property property){
        PropertyDto property1 = propertyIMPL.addProperty(countryId, locationId, property);
        return new ResponseEntity<>(property1, HttpStatus.CREATED);
    }
    @DeleteMapping
    public ResponseEntity<String>deleteProperty(@RequestParam long propertyId){
        propertyIMPL.deleteProperty(propertyId);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }
    @PutMapping("/{propertyId}/{countryId}/{locationId}")
    public ResponseEntity<PropertyDto>updateProperty(
            @PathVariable long propertyId, @PathVariable long countryId,
            @PathVariable long locationId, @RequestBody PropertyDto dto){
        PropertyDto propertyDto = propertyIMPL.updateProperty(propertyId, countryId, locationId, dto);
        return new ResponseEntity<>(propertyDto,HttpStatus.OK);
    }
    @GetMapping("/search/properties")
    public ResponseEntity<List<PropertyDto>>getAllProperty(
            @RequestParam(name="pageSize",defaultValue = "5",required = false) int pageSize,
            @RequestParam(name="pageNo",defaultValue = "5",required = false) int pageNo,
            @RequestParam(name="sortBy",defaultValue = "id",required = false) String sortBy,
            @RequestParam(name="name",required = false)String name,
            @RequestParam(name = "sortDir",defaultValue = "id",required = false) String sortDir){
        List<PropertyDto> allProperty = propertyIMPL.getAllProperty(pageSize, pageNo, sortBy,name,sortDir);
        return new ResponseEntity<>(allProperty,HttpStatus.OK);
    }
    @GetMapping("/getPropertyId")
    public ResponseEntity<Property>findByPropertyId(@RequestParam long propertyId){
        Property byId = propertyIMPL.getById(propertyId);
        return new ResponseEntity<>(byId,HttpStatus.OK);
    }

    @GetMapping("/search/getLocationName")
    public ResponseEntity<List<Property>>searchProperty(@RequestParam String name){
        List<Property> properties = propertyIMPL.searchProperty(name);
        return new ResponseEntity<>(properties,HttpStatus.OK);
    }
    

}
