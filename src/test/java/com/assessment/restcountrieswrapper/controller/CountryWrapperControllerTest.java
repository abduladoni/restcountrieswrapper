package com.assessment.restcountrieswrapper.controller;

import com.assessment.restcountrieswrapper.domain.base.Name;
import com.assessment.restcountrieswrapper.domain.v3.v31.Country;
import com.assessment.restcountrieswrapper.exception.DataNotFoundException;
import com.assessment.restcountrieswrapper.exception.InternalServerError;
import com.assessment.restcountrieswrapper.service.CountryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CountryWrapperController.class)
class CountryWrapperControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService countryService;

    @AfterEach
    public void afterEach(){
        reset(countryService);
    }

    @Test
    void testToReturnCountriesByPopulationDensitySuccessfully() throws Exception {
        //Given
        List<Country> countries = List.of(
                                    createCountry("China", List.of("AFG","BTN","MMR","HKG","IND","KAZ"),
                                            9706961.0, 1402112000),
                                    createCountry("Bhutan", List.of("CHN","IND"),
                                            38394.0, 771612));
        when(countryService.getAllCountriesByPopulationDensity()).thenReturn(countries);

        //When
        //Then
        mockMvc.perform(get("/restcountries/v1/countries/sort/populationdensity"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]['population']").value(1402112000))
                .andExpect(jsonPath("$[0]['area']").value(9706961.0))
                .andDo(print());
    }

    @Test
    void testToReturnCountryByMostBordersSuccessfully() throws Exception {
        //Given
        when(countryService.getCountryWithMostBorders("Asia")).
                thenReturn(createCountry("China", List.of("AFG","BTN","MMR","HKG","IND","KAZ"),
                9706961.0, 1402112000));

        //When
        //Then
        mockMvc.perform(get("/restcountries/v1/country/withmostborders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['population']").value(1402112000))
                .andExpect(jsonPath("$['area']").value(9706961.0))
                .andDo(print());

    }

    @Test
    void testCountryByMostBordersThrowsDataNotFoundExceptionWhenCountriesAPIHasNoData() throws Exception {
        //Given
        when(countryService.getCountryWithMostBorders("Asia"))
                .thenThrow(new DataNotFoundException("Countries API data is not available"));
        String continentNameValue = "Asia";

        //When
        //Then
        mockMvc.perform(get("/restcountries/v1/country/withmostborders").param("continentName",
                        continentNameValue))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$['statusCode']").value(404))
                .andExpect(jsonPath("$['message']").value("Countries API data is not available"))
                .andDo(print());

    }

    @Test
    void testCountryByMostBordersThrowsInternalServerErrorWhenCountriesAPICallBreaks() throws Exception {
        //Given
        when(countryService.getCountryWithMostBorders("Asia"))
                .thenThrow(new InternalServerError("Internal Server Error"));
        String continentNameValue = "Asia";

        //When
        //Then
        mockMvc.perform(get("/restcountries/v1/country/withmostborders").param("continentName",
                        continentNameValue))
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$['statusCode']").value(500))
                .andExpect(jsonPath("$['message']").value("Internal Server Error"))
                .andDo(print());

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
       return country;

    }
}