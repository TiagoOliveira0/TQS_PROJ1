package com.demo.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class AirQualityGeoLocationServiceTest {

    @Mock( lenient = true)
    private AirQualityGeoLocationService airQualityGeoLocationService;

    @BeforeEach
    public void setup(){
        City c1 = new City("40.5284837","-8.7655529","Aveiro");

        Mockito.when(airQualityGeoLocationService.getCoordsByCity("Aveiro")).thenReturn(c1);
    }

    @Test
    public void whenGetCoordsByCity_ThenCityShouldBeProvided(){
        String city = "Aveiro";
        String lat = "40.5284837";
        String lon = "-8.7655529";

        City found = airQualityGeoLocationService.getCoordsByCity(city);

        assertThat(found.getCity()).isEqualTo(city);
        assertThat(found.getLat()).isEqualTo(lat);
        assertThat(found.getLon()).isEqualTo(lon);

    }
}
