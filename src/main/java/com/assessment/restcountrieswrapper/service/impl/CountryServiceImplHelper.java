package com.assessment.restcountrieswrapper.service.impl;

import com.assessment.restcountrieswrapper.domain.v3.v31.Country;
import com.assessment.restcountrieswrapper.exception.DataNotFoundException;
import com.assessment.restcountrieswrapper.exception.InternalServerError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class CountryServiceImplHelper {

    @Value("${countries.api.url}")
    private String countriesUrl;

    @Autowired
    private RestTemplate restTemplate;

    public List<Country> getAllCountries() {
        log.info(">> Invoking Countries API");
        try {
            ResponseEntity<Country[]> response = restTemplate.getForEntity(countriesUrl, Country[].class);
            Country[] countries = response.getBody();
            if (null != countries && countries.length > 0) {
                log.info("<< Finished invoking Countries API with response countries size: {}", countries.length);
                return Arrays.asList(countries);
            }
            throw new DataNotFoundException("Countries API data is not available");
        } catch (Exception exception) {
            if(exception instanceof DataNotFoundException){
                throw exception;
            }
            log.error("> Exception occurred while retrieving countries API: ", exception);
            throw new InternalServerError("Internal Server Error");
        }
    }

}
