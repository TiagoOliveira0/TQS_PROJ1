package com.demo.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class AirQualityServiceTest {

    @Mock( lenient = true)
    private AirQualityService airQualityService;

    @BeforeEach
    public void setUp() {
        City c1 = new City("Aveiro");
        Air a1 = new Air("8/5/2021",0,0,0,0,0,15,0,0,0,0);
        Air a2 = new Air("9/5/2021",0,0,0,0,0,15,0,0,0,0);
        Air a3 = new Air("10/5/2021",0,0,0,0,0,15,0,0,0,0);
        Air a4 = new Air("11/5/2021",0,0,0,0,0,15,0,0,0,0);
        Air a5 = new Air("12/5/2021",0,0,0,0,0,15,0,0,0,0);
        List<Air> lista = new ArrayList<>();
        lista.add(a1);
        lista.add(a2);
        lista.add(a3);
        lista.add(a4);
        lista.add(a5);


        Air a6 = new Air("7/5/2021",0,0,0,0,0,0,0,0,0,0);
        Air a7 = new Air("6/5/2021",0,0,0,0,0,0,0,0,0,0);
        Air a8 = new Air("5/5/2021",0,0,0,0,0,0,0,0,0,0);
        Air a9 = new Air("4/5/2021",0,0,0,0,0,0,0,0,0,0);
        Air a10 = new Air("3/5/2021",0,0,0,0,0,0,0,0,0,0);
        List<Air> lista2 = new ArrayList<>();
        lista2.add(a6);
        lista2.add(a7);
        lista2.add(a8);
        lista2.add(a9);
        lista2.add(a10);

        Mockito.when(airQualityService.getAirByNow(c1)).thenReturn(a1);

        Mockito.when(airQualityService.getAirNextDays(c1)).thenReturn(lista);

        Mockito.when(airQualityService.getAirLastDays(c1)).thenReturn(lista2);
    }

    @AfterEach
    public void teardown(){
        ;
    }

    @Test
    public void whenGetAirByNow_ThenCityShouldBeProvided(){
        float  o3 = 15;
        City c1  = new City("Aveiro");

        Air found = airQualityService.getAirByNow(c1);

        assertThat(found.getO3()).isEqualTo(o3);

    }

    @Test
    public void whenGetAirNextDays_ThenCityShouldBeProvided(){
        String date1 = "8/5/2021";
        String date2 = "9/5/2021";
        String date3 = "10/5/2021";
        String date4 = "11/5/2021";
        String date5 = "12/5/2021";

        City c1  = new City("Aveiro");

        List<Air> found = airQualityService.getAirNextDays(c1);

        assertThat(found.size()).isEqualTo(5);
        assertThat(found.get(0).getDate()).isEqualTo(date1);
        assertThat(found.get(1).getDate()).isEqualTo(date2);
        assertThat(found.get(2).getDate()).isEqualTo(date3);
        assertThat(found.get(3).getDate()).isEqualTo(date4);
        assertThat(found.get(4).getDate()).isEqualTo(date5);

    }

    @Test
    public void whenGetAirLastDays_ThenCityShouldBeProvided(){
        String date1 = "7/5/2021";
        String date2 = "6/5/2021";
        String date3 = "5/5/2021";
        String date4 = "4/5/2021";
        String date5 = "3/5/2021";

        City c1  = new City("Aveiro");

        List<Air> found = airQualityService.getAirLastDays(c1);

        assertThat(found.size()).isEqualTo(5);
        assertThat(found.get(0).getDate()).isEqualTo(date1);
        assertThat(found.get(1).getDate()).isEqualTo(date2);
        assertThat(found.get(2).getDate()).isEqualTo(date3);
        assertThat(found.get(3).getDate()).isEqualTo(date4);
        assertThat(found.get(4).getDate()).isEqualTo(date5);

    }
}
