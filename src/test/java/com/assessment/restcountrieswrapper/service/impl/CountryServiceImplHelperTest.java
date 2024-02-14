package com.assessment.restcountrieswrapper.service.impl;

import com.assessment.restcountrieswrapper.domain.base.Name;
import com.assessment.restcountrieswrapper.domain.v3.v31.Country;
import com.assessment.restcountrieswrapper.exception.DataNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = {"countries.api.url=https://exampletest.com/v3.1/all"})
class CountryServiceImplHelperTest {

    @TestConfiguration
    static class CountryServiceImplHelperTestContextConfiguration {

        @Bean
        public CountryServiceImplHelper employeeService() {
            return new CountryServiceImplHelper();
        }
    }

    @MockBean
    private RestTemplate restTemplate;

    @Value("${countries.api.url}")
    private String countriesUrl;

    @Autowired
    CountryServiceImplHelper serviceImplHelperTestObject;

    @Test
    void testThatGetAllCountriesApiInvokedSuccessfully() {

        //Given
        Country[] countries = {
                createCountry("Bhutan", List.of("CHN", "IND"),
                        38394.0, 771612),
                createCountry("China", List.of("AFG", "BTN", "MMR", "HKG", "IND", "KAZ"),
                        9706961.0, 1402112000)
        };
        ResponseEntity<Country[]> response  = ResponseEntity.status(HttpStatus.OK).body(countries);
        when(restTemplate.getForEntity(countriesUrl, Country[].class)).thenReturn(response);

        //When
        List<Country> allCountries = serviceImplHelperTestObject.getAllCountries();

        //Then
        assertEquals(2, allCountries.size());
    }

    @Test
    void testThatGetAllCountriesThrowsExceptionWhenApiReturnEmptyResponse() {

        //Given
        Country[] countries = {};
        ResponseEntity<Country[]> response  = ResponseEntity.status(HttpStatus.OK).body(countries);
        when(restTemplate.getForEntity(countriesUrl, Country[].class)).thenReturn(response);

        //When
        Throwable thrown = assertThrows(DataNotFoundException.class,
                ()-> serviceImplHelperTestObject.getAllCountries());
        //Then
        assertEquals("Countries API data is not available",  thrown.getMessage());
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