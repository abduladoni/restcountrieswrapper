package com.assessment.restcountrieswrapper.service.impl;

import com.assessment.restcountrieswrapper.domain.v3.v31.Country;
import com.assessment.restcountrieswrapper.exception.DataNotFoundException;
import com.assessment.restcountrieswrapper.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryServiceImplHelper countryServiceImplHelper;

    public List<Country> getAllCountriesByPopulationDensity() {
        List<Country> countries = getAllCountries();
        if (countries.size() > 1) {
            log.info("Sorting countries based on population density in desc order");
            return countries.stream().sorted((country1, country2) -> {
                Double populationDensity1 = country1.getPopulation() / country1.getArea();
                Double populationDensity2 = country2.getPopulation() / country2.getArea();
                return populationDensity2.compareTo(populationDensity1);
            }).toList();
        }
        return countries;
    }

    @Override
    public Country getCountryWithMostBorders(final String continentName) {

        List<Country> countries = getAllCountries();
        if (countries.size() > 1) {
            List<Country> matchingCountries = getCountriesByContinentName(continentName, countries);
            log.info("Processing country with most borders");
            return matchingCountries.stream().sorted((country1, country2) -> {

                Integer bordersSize1 = country1.getBorders() == null ? 0 : country1.getBorders().size();
                Integer bordersSize2 = country2.getBorders() == null ? 0 : country2.getBorders().size();
                return bordersSize2.compareTo(bordersSize1);
            }).toList().get(0);
        }
        return countries.get(0);
    }

    private List<Country> getAllCountries() {
        return countryServiceImplHelper.getAllCountries();
    }

    private List<Country> getCountriesByContinentName(final String continentName, final List<Country> countries) {
        List<Country> matchingCountries = countries.stream()
                .filter(country -> country.getContinents().contains(continentName))
                .toList();

        if (matchingCountries.isEmpty()) {
            throw new DataNotFoundException(String.format("Countries for the given continent: %s not found." +
                    " Continent Name is Invalid", continentName));
        }
        log.info("Countries matching the given continent name: {} size: {}", continentName, matchingCountries.size());
        return matchingCountries;
    }


}
