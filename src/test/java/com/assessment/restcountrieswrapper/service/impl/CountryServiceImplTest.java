package com.assessment.restcountrieswrapper.service.impl;

import com.assessment.restcountrieswrapper.domain.base.Name;
import com.assessment.restcountrieswrapper.domain.v3.v31.Country;
import com.assessment.restcountrieswrapper.exception.DataNotFoundException;
import com.assessment.restcountrieswrapper.service.CountryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
class CountryServiceImplTest {

    @TestConfiguration
    static class CountryServiceImplTestContextConfiguration {

        @Bean
        public CountryService employeeService() {
            return new CountryServiceImpl();
        }
    }

    @MockBean
    private CountryServiceImplHelper countryServiceImplHelper;

    @Autowired
    private CountryService countryServiceTestObject;

    @AfterEach
    public void afterEach(){
        reset(countryServiceImplHelper);
    }

    @Test
    void testGetCountriesByPopulationDensitySortsSuccessfully() {
        //Given
        List<Country> countries = List.of(
                createCountry("Bhutan", List.of("CHN","IND"),
                        38394.0, 771612),
                createCountry("China", List.of("AFG","BTN","MMR","HKG","IND","KAZ"),
                        9706961.0, 1402112000)
                );
        when(countryServiceImplHelper.getAllCountries()).thenReturn(countries);

        //When
        List<Country> responseCountriesList = countryServiceTestObject.getAllCountriesByPopulationDensity();

        //Then
        assertEquals(2, responseCountriesList.size());
        assertEquals("China", responseCountriesList.get(0).getName().getCommon());
    }

    @Test
    void testGetCountryWithMostBordersWhenValidDateIsProvided() {
        //Given
        List<Country> countries = List.of(
                createCountry("Bhutan", List.of("CHN","IND"),
                        38394.0, 771612),
                createCountry("China", List.of("AFG","BTN","MMR","HKG","IND","KAZ"),
                        9706961.0, 1402112000)
        );
        when(countryServiceImplHelper.getAllCountries()).thenReturn(countries);

        //When
        Country responseCountry = countryServiceTestObject.getCountryWithMostBorders("Asia");

        //Then
        assertEquals("China", responseCountry.getName().getCommon());
        assertEquals(6, responseCountry.getBorders().size());

    }

    @Test
    void testGetCountryWithMostBordersThrowsExceptionWhenContinentNameIsNotMatching() {
        //Given
        List<Country> countries = List.of(
                createCountry("Bhutan", List.of("CHN","IND"),
                        38394.0, 771612),
                createCountry("China", List.of("AFG","BTN","MMR","HKG","IND","KAZ"),
                        9706961.0, 1402112000)
        );
        when(countryServiceImplHelper.getAllCountries()).thenReturn(countries);

        //When
        Throwable thrown = assertThrows(DataNotFoundException.class,
                ()-> countryServiceTestObject.getCountryWithMostBorders("Antarctica"));
        //Then
        assertEquals("Countries for the given continent: Antarctica not found. Continent Name is Invalid",
                thrown.getMessage());

    }

    private Country createCountry(final String countryName,
                                  final List <String> borders,
                                  final Double area,
                                  final Integer population){
        Country country = new Country();
        Name name = new Name();
        name.setCommon(countryName);
        country.setName(name);
        country.setBorders(borders);
        country.setArea(area);
        country.setPopulation(population);
        country.setContinents(List.of("Asia", "Africa"));
        return country;
    }
}