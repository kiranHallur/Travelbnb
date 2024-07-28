package com.travelbnb.repository;

import com.travelbnb.entity.Country;
import com.travelbnb.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface  CountryRepository extends JpaRepository<Country, Long> {


}