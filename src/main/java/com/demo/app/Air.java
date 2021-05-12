package com.demo.app;

import java.util.Objects;

public class Air {
    private float lat;
    private float lon;
    private float co;
    private float no;
    private float no2;
    private float o3;
    private float so2;
    private float pm25;
    private float pm10;
    private float nh3;
    private String date;


    public Air(String date, float lat, float lon, float co, float no, float no2, float o3, float so2, float pm25, float pm10, float nh3) {
        this.date = date;
        this.lat = lat;
        this.lon = lon;
        this.co = co;
        this.no = no;
        this.no2 = no2;
        this.o3 = o3;
        this.so2 = so2;
        this.pm25 = pm25;
        this.pm10 = pm10;
        this.nh3 = nh3;
    }


    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }

    public float getCo() {
        return co;
    }

    public float getNo() {
        return no;
    }

    public float getNo2() {
        return no2;
    }

    public float getO3() {
        return o3;
    }

    public float getSo2() {
        return so2;
    }

    public float getPm2_5() {
        return pm25;
    }

    public float getPm10() {
        return pm10;
    }
    public String getDate() {
        return date;
    }

    public float getNh3() {
        return nh3;
    }


    @Override
    public int hashCode() {
        return Objects.hash(lat, lon, co, no, no2, o3, so2, pm25, pm10, nh3);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Air var = (Air) o;
        return Float.compare(var.lat, lat) == 0 && Float.compare(var.lon, lon) == 0 && Float.compare(var.co, co) == 0 && Float.compare(var.no, no) == 0 && Float.compare(var.no2, no2) == 0 && Float.compare(var.o3, o3) == 0 && Float.compare(var.so2, so2) == 0 && Float.compare(var.pm25, pm25) == 0 && Float.compare(var.pm10, pm10) == 0 && Float.compare(var.nh3, nh3) == 0 && Objects.equals(date, var.date);
    }

}
