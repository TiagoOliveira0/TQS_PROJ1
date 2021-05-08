package com.demo.app;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AirQualityGeoLocationServiceImpl implements AirQualityGeoLocationService{

    Logger logger = LoggerFactory.getLogger(AirQualityGeoLocationServiceImpl.class);

    @Value("${api2.key}")
    private String api2Key;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public City getCoordsByCity(String city) {
        String json1 = restTemplate.getForObject("https://api.opencagedata.com/geocode/v1/json?q=" + city + "&key=" + api2Key, String.class);

        JSONObject json = null;

        try {
            json = new JSONObject(json1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(json==null){
            return null;
        }

        String lat = null;
        String lon = null;

        try {
            lat = json.getJSONArray("results").getJSONObject(0).getJSONObject("bounds").getJSONObject("southwest").getString("lat");
            lon = json.getJSONArray("results").getJSONObject(0).getJSONObject("bounds").getJSONObject("southwest").getString("lng");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(lat==null || lon==null) {
            logger.error("OpencageData external api accessed to request the coordinates for the place called " + city + " but no coordinates were found.");
            return new City(null, null);
        }


        logger.info("OpencageData external api accessed to request the coordinates for the place called " + city + ".");
        return new City(lat,lon,city);
    }
}
