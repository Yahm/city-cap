package com.yahm.citycap.services;

import com.yahm.citycap.models.Country;

import java.util.List;

public interface CountryService {
    List<Country> getCountries();
    List<Country> getCountryByName(String name) throws Exception;
}
