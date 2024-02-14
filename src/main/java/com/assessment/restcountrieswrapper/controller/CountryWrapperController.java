package com.assessment.restcountrieswrapper.controller;

import com.assessment.restcountrieswrapper.domain.v3.v31.Country;
import com.assessment.restcountrieswrapper.exception.ApiErrorResponse;
import com.assessment.restcountrieswrapper.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="restcountries/v1/")
@Slf4j
public class CountryWrapperController {

    @Autowired
    private CountryService countryService;

    @Operation(summary="Get countries", description="Get a list of countries sorted by population density",tags="Get")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description="List of countries sorted by population density"),
            @ApiResponse(responseCode = "404", description="Countries Not found",
                            content = { @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description="Internal Server Error",
                            content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)) })
    })
    @GetMapping("countries/sort/populationdensity")
    public List<Country> countriesByPopulationDensity() {
       log.info(">>> Invoking Sort Countries by population density");
       return countryService.getAllCountriesByPopulationDensity();
    }

    @Operation(summary="Get Country", description="Get a country which has most borders",tags="Get")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description="Country with most borders",
                        content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Country.class)) }),
            @ApiResponse(responseCode = "404", description="Countries Not found/Countries with given continent name Not Found",
                        content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ApiErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description="Internal Server Error",
                        content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ApiErrorResponse.class)) })
    })
    @GetMapping("/country/withmostborders")
    public Country countryByMostBorders(@RequestParam(value="continentName", defaultValue = "Asia") String continentName) {
        log.info(">>> Invoking Get Country by most borders");
        return countryService.getCountryWithMostBorders(continentName);
    }
}
