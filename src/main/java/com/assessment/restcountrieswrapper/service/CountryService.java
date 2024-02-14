package com.assessment.restcountrieswrapper.service;

import com.assessment.restcountrieswrapper.domain.v3.v31.Country;

import java.util.List;

public interface CountryService {

    public List<Country> getAllCountriesByPopulationDensity();
    public Country getCountryWithMostBorders(final String continentName);
}
