package com.demo.app;

import java.util.Objects;

public class City {

    private String lat;
    private String lon;
    private String city;

    public City(String lat, String lon, String city) {
        this.lat = lat;
        this.lon = lon;
        this.city = city;
    }

    public City(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city1 = (City) o;
        return Objects.equals(lat, city1.lat) && Objects.equals(lon, city1.lon) && Objects.equals(city, city1.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lon, city);
    }

    @Override
    public String toString() {
        return "City{" +
                "lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
