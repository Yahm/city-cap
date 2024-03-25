package com.yahm.citycap.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
    private CountryName name;
    private String[] capital;
    private String population;
    private String[] timezones;
    private String[] continents;
}
