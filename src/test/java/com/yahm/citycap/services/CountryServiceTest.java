package com.yahm.citycap.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CountryServiceTest {

    @MockBean
    CountryServiceImpl countryServiceImpl;

    @BeforeEach
    public void beforeAll() throws URISyntaxException, IOException, InterruptedException {
        countryServiceImpl = new CountryServiceImpl();
    }

    @Test
    void getAllCountries() {
        assertThat(countryServiceImpl.getCountries()).isNotNull();
        assertThat(countryServiceImpl.getCountries()).hasSizeGreaterThan(1);
    }
}
