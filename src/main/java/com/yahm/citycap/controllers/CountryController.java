package com.yahm.citycap.controllers;

import com.yahm.citycap.services.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/all")
    ResponseEntity<?> getCountries() {
        return new ResponseEntity<>(this.countryService.getCountries(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    ResponseEntity<?> getCountriesByName(@PathVariable("name") String name) throws Exception {
        return new ResponseEntity<>(this.countryService.getCountryByName(name), HttpStatus.OK);
    }
}
