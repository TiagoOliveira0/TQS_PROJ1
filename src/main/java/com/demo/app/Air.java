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

    public Air(String date, float lat, float lon, float co, float no, float no2, float o3) {
        this.date = date;
        this.lat = lat;
        this.lon = lon;
        this.co = co;
        this.no = no;
        this.no2 = no2;
        this.o3 = o3;
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

    public float getPm25() {
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

    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public void setCo(float co) {
        this.co = co;
    }

    public void setNo(float no) {
        this.no = no;
    }

    public void setNo2(float no2) {
        this.no2 = no2;
    }

    public void setO3(float o3) {
        this.o3 = o3;
    }

    public void setSo2(float so2) {
        this.so2 = so2;
    }

    public void setPm25(float pm25) {
        this.pm25 = pm25;
    }

    public void setPm10(float pm10) {
        this.pm10 = pm10;
    }

    public void setNh3(float nh3) {
        this.nh3 = nh3;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public int hashCode() {
        return Objects.hash(lat, lon, co, no, no2, o3, so2, pm25, pm10, nh3);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Air a = (Air) o;
        return Float.compare(a.lat, lat) == 0 && Float.compare(a.lon, lon) == 0 && Float.compare(a.co, co) == 0 && Float.compare(a.no, no) == 0 && Float.compare(a.no2, no2) == 0 && Float.compare(a.o3, o3) == 0 && Float.compare(a.so2, so2) == 0 && Float.compare(a.pm25, pm25) == 0 && Float.compare(a.pm10, pm10) == 0 && Float.compare(a.nh3, nh3) == 0 && Objects.equals(date, a.date);
    }

}
