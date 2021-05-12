package com.demo.app;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Service
public class AirQualityServiceImpl implements AirQualityService{

    Logger logger = LoggerFactory.getLogger(AirQualityServiceImpl.class);

    private String l = "&lon=";

    private String p = "&appid=";

    private String f = "dd-MM-yyy";

    private String com = "components";

    private String P25 = "pm2_5";

    @Value("${api1.key}")
    private String api1Key;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AirQualityGeoLocationService airQualityGeoLocationService;

    @Override
    public Air getAirByNow(City city) {
        String json1 = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/air_pollution?lat=" + city.getLat() + l + city.getLon() + p + api1Key, String.class);

        JSONObject json = null;
        try {
            json = new JSONObject(json1);
        } catch (JSONException e) {
            logger.error("No JSON was found.");
        }

        if(json==null){
            logger.error("JSON not viable to convert into JSONObject.");
            return new Air("",0,0,0,0,0,0);
        }

        String date = null;
        Float no2 = null;
        Float no = null;
        Float o3 = null;
        Float so2 = null;
        Float pm25 = null;
        Float pm10 = null;
        Float nh3 = null;
        Float co = null;

        try {

            Float dt = Float.parseFloat(json.getJSONArray("list").getJSONObject(0).getString("dt"));
            Date dte = new Date((long) (dt*1000L));
            SimpleDateFormat dateFormat = new SimpleDateFormat(f);
            date = dateFormat.format(dte);
            no2 = Float.parseFloat(json.getJSONArray("list").getJSONObject(0).getJSONObject(com).getString("no2"));
            no = Float.parseFloat(json.getJSONArray("list").getJSONObject(0).getJSONObject(com).getString("no"));
            o3 = Float.parseFloat(json.getJSONArray("list").getJSONObject(0).getJSONObject(com).getString("o3"));
            so2 = Float.parseFloat(json.getJSONArray("list").getJSONObject(0).getJSONObject(com).getString("so2"));
            pm25 = Float.parseFloat(json.getJSONArray("list").getJSONObject(0).getJSONObject(com).getString(P25));
            pm10 = Float.parseFloat(json.getJSONArray("list").getJSONObject(0).getJSONObject(com).getString("pm10"));
            nh3 = Float.parseFloat(json.getJSONArray("list").getJSONObject(0).getJSONObject(com).getString("nh3"));
            co = Float.parseFloat(json.getJSONArray("list").getJSONObject(0).getJSONObject(com).getString("co"));
        } catch (JSONException e) {
            logger.error("One of the JSON fields was null and so it was not possible to convert it.");
        }

        if(no2!=null && no!=null && o3!=null && so2!=null && pm25!=null && pm10!=null && nh3!=null && co!=null){
            Air a = new Air(date,Float.parseFloat(city.getLat()),Float.parseFloat(city.getLon()),co,no,no2,o3);
            a.setSo2(so2);
            a.setPm25(pm25);
            a.setPm10(pm10);
            a.setNh3(nh3);
            return a;
        }

        return new Air("",0,0,0,0,0,0);
    }

    @Override
    public List<Air> getAirNextDays(City city){
        long now = Instant.now().getEpochSecond();

        Instant instant1 = Instant.ofEpochSecond(now);

        int today = LocalDateTime.ofInstant(instant1, ZoneOffset.UTC).getDayOfYear();

        Integer last = null;

        String json1 = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/air_pollution/forecast?lat=" + city.getLat() + l + city.getLon() + p + api1Key, String.class);

        JSONObject json = null;
        try {
            json = new JSONObject(json1);
        } catch (JSONException e) {
            logger.error("No JSON found.");
            return new ArrayList<>();
        }

        if(json==null){
            logger.error("JSON was not viable to convert into JSONObject.");
            return new ArrayList<>();
        }

        List<Air> lista = new ArrayList<>();

        int tam = 0;

        try {
            tam = json.getJSONArray("list").length();
        } catch (JSONException e) {
            logger.warn("JSON not viable to the convertion.");
        }

        if(tam==0){
            logger.warn("Request to the API came empty.");
            return new ArrayList<>();
        }   

        for(int i=0; i<tam;i++) {
            String date = null;
            Float no2 = null;
            Float no = null;
            Float o3 = null;
            Float so2 = null;
            Float pm25 = null;
            Float pm10 = null;
            Float nh3 = null;
            Float co = null;

            Integer day = null;

            try {
                Float dt = Float.parseFloat(json.getJSONArray("list").getJSONObject(i).getString("dt"));
                Date dte = new Date((long) (dt*1000L));
                SimpleDateFormat dateFormat = new SimpleDateFormat(f);
                date = dateFormat.format(dte);
                no2 = Float.parseFloat(json.getJSONArray("list").getJSONObject(i).getJSONObject(com).getString("no2"));
                no = Float.parseFloat(json.getJSONArray("list").getJSONObject(i).getJSONObject(com).getString("no"));
                o3 = Float.parseFloat(json.getJSONArray("list").getJSONObject(i).getJSONObject(com).getString("o3"));
                so2 = Float.parseFloat(json.getJSONArray("list").getJSONObject(i).getJSONObject(com).getString("so2"));
                pm25 = Float.parseFloat(json.getJSONArray("list").getJSONObject(i).getJSONObject(com).getString(P25));
                pm10 = Float.parseFloat(json.getJSONArray("list").getJSONObject(i).getJSONObject(com).getString("pm10"));
                nh3 = Float.parseFloat(json.getJSONArray("list").getJSONObject(i).getJSONObject(com).getString("nh3"));
                co = Float.parseFloat(json.getJSONArray("list").getJSONObject(i).getJSONObject(com).getString("co"));

                day = Integer.parseInt(json.getJSONArray("list").getJSONObject(i).getString("dt"));

                Instant instant = Instant.ofEpochSecond(day);

                day = LocalDateTime.ofInstant(instant, ZoneOffset.UTC).getDayOfYear();

            } catch (JSONException e) {
                logger.error("JSONexcepetion trying to convert it.");
                return new ArrayList<>();
            }

            if(last == null && day!=null){
                last=day;
            }

            if(no2!=null && no!=null && o3!=null && so2!=null && pm25!=null && pm10!=null && nh3!=null && co!=null && day!=null && !day.equals(last) && day>today){
                Air a = new Air(date,Float.parseFloat(city.getLat()),Float.parseFloat(city.getLon()),co,no,no2,o3);
                a.setSo2(so2);
                a.setPm25(pm25);
                a.setPm10(pm10);
                a.setNh3(nh3);

                lista.add(a);
                last=day;
            }
        }

        if(lista.size()!=0){
            return lista;
        }

        logger.error("No data found.");
        return new ArrayList<>();

    }

    @Override
    public List<Air> getAirLastDays(City c) {
        long now = Instant.now().getEpochSecond();

        Instant instant1 = Instant.ofEpochSecond(now);

        int today = LocalDateTime.ofInstant(instant1, ZoneOffset.UTC).getDayOfYear();

        Integer last = null;

        String json1 = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/air_pollution/forecast?lat=" + c.getLat() + l + c.getLon() + p + api1Key, String.class);

        JSONObject json = null;
        try {
            json = new JSONObject(json1);
        } catch (JSONException e) {
            logger.error("JSONExcepetion trying to converting it.");
            return new ArrayList<>();
        }

        if(json==null){
            logger.error("JSON requested came null.");
            return new ArrayList<>();
        }

        List<Air> lista = new ArrayList<>();

        int tam = 0;

        try {
            tam = json.getJSONArray("list").length();
        } catch (JSONException e) {
            logger.error("JSONException trying to get a parameter from it.");
        }

        if(tam==0){
            logger.error("JSON requested came null.");
            return new ArrayList<>();
        }

        for(int i=0; i<tam;i++) {
            String date = null;
            Float no2 = null;
            Float no = null;
            Float o3 = null;
            Float so2 = null;
            Float pm25 = null;
            Float pm10 = null;
            Float nh3 = null;
            Float co = null;

            Integer day = null;

            try {
                Float dt = Float.parseFloat(json.getJSONArray("list").getJSONObject(i).getString("dt"));
                Date dte = new Date((long) (dt*1000L));
                SimpleDateFormat dateFormat = new SimpleDateFormat(f);
                date = dateFormat.format(dte);
                no2 = Float.parseFloat(json.getJSONArray("list").getJSONObject(i).getJSONObject(com).getString("no2"));
                no = Float.parseFloat(json.getJSONArray("list").getJSONObject(i).getJSONObject(com).getString("no"));
                o3 = Float.parseFloat(json.getJSONArray("list").getJSONObject(i).getJSONObject(com).getString("o3"));
                so2 = Float.parseFloat(json.getJSONArray("list").getJSONObject(i).getJSONObject(com).getString("so2"));
                pm25 = Float.parseFloat(json.getJSONArray("list").getJSONObject(i).getJSONObject(com).getString(P25));
                pm10 = Float.parseFloat(json.getJSONArray("list").getJSONObject(i).getJSONObject(com).getString("pm10"));
                nh3 = Float.parseFloat(json.getJSONArray("list").getJSONObject(i).getJSONObject(com).getString("nh3"));
                co = Float.parseFloat(json.getJSONArray("list").getJSONObject(i).getJSONObject(com).getString("co"));

                day = Integer.parseInt(json.getJSONArray("list").getJSONObject(i).getString("dt"));

                Instant instant = Instant.ofEpochSecond(day);

                day = LocalDateTime.ofInstant(instant, ZoneOffset.UTC).getDayOfYear();

            } catch (JSONException e) {
                logger.error("JSONException trying to convert its fields.");
                return new ArrayList<>();
            }

            if(last == null && day!=null){
                last=day;
            }

            if(no2!=null && no!=null && o3!=null && so2!=null && pm25!=null && pm10!=null && nh3!=null && co!=null && day!=null && !day.equals(last) && day<=today){
                Air a = new Air(date,Float.parseFloat(c.getLat()),Float.parseFloat(c.getLon()),co,no,no2,o3);
                a.setSo2(so2);
                a.setPm25(pm25);
                a.setPm10(pm10);
                a.setNh3(nh3);

                lista.add(a);
                last=day;
            }

        }

        if(lista.size()!=0){
            return lista;
        }

        logger.error("No data found in the request.");
        return new ArrayList<>();
    }
}
