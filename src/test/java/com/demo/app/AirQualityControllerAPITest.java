package com.demo.app;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AirRestController.class)
public class AirQualityControllerAPITest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AirQualityService airQualityService;

    @MockBean
    private AirQualityGeoLocationService airQualityGeoLocationService;

    @Test
    public void whenGetAirqualityNow_thenReturnAirQuality( ) throws Exception {
        City c1 = new City("40.5284837","-8.7655529","Aveiro");
        Air a1 = new Air("09-05-2021",Float.parseFloat("40.528484"),Float.parseFloat("-8.7655525"),Float.parseFloat("198.6"),Float.parseFloat("0.06"),Float.parseFloat("0.37"),Float.parseFloat("94.41"),Float.parseFloat("1.49"),Float.parseFloat("3.01"),Float.parseFloat("5.98"),0);

        when( airQualityService.getAirByNow(Mockito.any()) ).thenReturn(a1);

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("city", c1.getCity());

        mvc.perform(get("/AirQuality/now").params(requestParams).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.lat", is(a1.getLat()))).andExpect(jsonPath("$.lon", is(a1.getLon()))).andExpect(jsonPath("$.date", is(a1.getDate())));

        verify(airQualityService, times(1)).getAirByNow(Mockito.any());

    }

    @Test
    public void whenGetAirqualityNext_thenReturnAirQuality( ) throws Exception {
        City c1 = new City("40.5284837","-8.7655529","Aveiro");
        Air a1 = new Air("10-05-2021",Float.parseFloat("40.528484"),Float.parseFloat("-8.7655525"),Float.parseFloat("200.27"),Float.parseFloat(" 0.0"),Float.parseFloat("0.73"),Float.parseFloat("87.26"),Float.parseFloat("1.3"),Float.parseFloat("2.98"),Float.parseFloat("5.4"),0);
        Air a2 = new Air("11-05-2021",Float.parseFloat("40.528484"),Float.parseFloat("-8.7655525"),Float.parseFloat("208.62"),Float.parseFloat(" 0.0"),Float.parseFloat("0.49"),Float.parseFloat("86.55"),Float.parseFloat("1.16"),Float.parseFloat("1.66"),Float.parseFloat("4.28"),0);
        Air a3 = new Air("12-05-2021",Float.parseFloat("40.528484"),Float.parseFloat("-8.7655525"),Float.parseFloat("196.93"),Float.parseFloat(" 0.0"),Float.parseFloat("0.52"),Float.parseFloat("98.71"),Float.parseFloat("0.8"),Float.parseFloat("1.15"),Float.parseFloat("4.26"),Float.parseFloat("0.01"));
        Air a4 = new Air("13-05-2021",Float.parseFloat("40.528484"),Float.parseFloat("-8.7655525"),Float.parseFloat("191.93"),Float.parseFloat(" 0.0"),Float.parseFloat("0.79"),Float.parseFloat("81.54"),Float.parseFloat("1.15"),Float.parseFloat("8.5"),Float.parseFloat("15.49"),0);
        Air a5 = new Air("14-05-2021",Float.parseFloat("40.528484"),Float.parseFloat("-8.7655525"),Float.parseFloat("195.27"),Float.parseFloat(" 0.0"),Float.parseFloat("0.8"),Float.parseFloat("83.69"),Float.parseFloat("1.18"),Float.parseFloat("2.93"),Float.parseFloat("8.61"),0);

        List<Air> air = new ArrayList<>();

        air.add(a1);
        air.add(a2);
        air.add(a3);
        air.add(a4);
        air.add(a5);

        when(airQualityService.getAirNextDays(Mockito.any()) ).thenReturn(air);

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("city", c1.getCity());

        mvc.perform(get("/AirQuality/next").params(requestParams).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.city", is(c1.getCity())));

        verify(airQualityService, times(1)).getAirByNow(Mockito.any());

    }

    @Test
    public void whenGetAirqualityLast_thenReturnAirQuality( ) throws Exception {
        City c1 = new City("40.5284837","-8.7655529","Aveiro");
        Air a1 = new Air("05-05-2021",Float.parseFloat("40.528484"),Float.parseFloat("-8.7655525"),Float.parseFloat("186.92"),Float.parseFloat(" 0.0"),Float.parseFloat("0.86"),Float.parseFloat("100.14"),Float.parseFloat("0.92"),Float.parseFloat("1.78"),Float.parseFloat("5.64"),0);
        Air a2 = new Air("06-05-2021",Float.parseFloat("40.528484"),Float.parseFloat("-8.7655525"),Float.parseFloat("188.59"),Float.parseFloat(" 0.0"),Float.parseFloat("1.07"),Float.parseFloat("83.69"),Float.parseFloat("0.66"),Float.parseFloat("2.28"),Float.parseFloat("4.58"),0);
        Air a3 = new Air("07-05-2021",Float.parseFloat("40.528484"),Float.parseFloat("-8.7655525"),Float.parseFloat("188.59"),Float.parseFloat(" 0.0"),Float.parseFloat("1.54"),Float.parseFloat("67.95"),Float.parseFloat("0.45"),Float.parseFloat("2.49"),Float.parseFloat("5.79"),Float.parseFloat("0.14"));
        Air a4 = new Air("08-05-2021",Float.parseFloat("40.528484"),Float.parseFloat("-8.7655525"),Float.parseFloat("183.58"),Float.parseFloat(" 0.0"),Float.parseFloat("1.09"),Float.parseFloat("86.55"),Float.parseFloat("0.85"),Float.parseFloat("4.83"),Float.parseFloat("10.49"),0);
        Air a5 = new Air("09-05-2021",Float.parseFloat("40.528484"),Float.parseFloat("-8.7655525"),Float.parseFloat("176.91"),Float.parseFloat(" 0.0"),Float.parseFloat("1.56"),Float.parseFloat("67.23"),Float.parseFloat("0.72"),Float.parseFloat("1.41"),Float.parseFloat("3.7"),Float.parseFloat("0.25"));

        List<Air> air = new ArrayList<>();

        air.add(a1);
        air.add(a2);
        air.add(a3);
        air.add(a4);
        air.add(a5);

        when(airQualityService.getAirLastDays(Mockito.any()) ).thenReturn(air);

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("city", c1.getCity());

        mvc.perform(get("/AirQuality/last").params(requestParams).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.city", is(c1.getCity())));

        verify(airQualityService, times(1)).getAirByNow(Mockito.any());

    }
}
