package com.yahm.citycap.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yahm.citycap.models.Country;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CountryServiceImpl implements CountryService {
    private final HttpClient httpClient;
    private List<Country> countryList;


    public CountryServiceImpl() throws URISyntaxException, IOException, InterruptedException {
        this.httpClient = HttpClient.newHttpClient();
        countryList = new ArrayList<>();
        getCountriesFromApi();
    }

    void getCountriesFromApi() throws URISyntaxException, IOException, InterruptedException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://restcountries.com/v3.1/all"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        String body = response.body();

        this.countryList = objectMapper.readValue(body, new TypeReference<List<Country>>(){});

        log.debug("country list {}", this.countryList );

    }

    @Override
    public List<Country> getCountries() {
        return this.countryList;
    }

    @Override
    public Country getCountryByName(String name) throws Exception {
        return this.countryList.stream().filter(country -> country.getName().getCommon().equals(name)).findFirst().orElseThrow(() -> new Exception("Country not found - " + name));
    }
}
