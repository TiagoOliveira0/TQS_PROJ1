package com.demo.app;

import java.util.Objects;

public class City {

    private String lat;
    private String lon;
    private String cname;

    public City(String lat, String lon, String cname) {
        this.lat = lat;
        this.lon = lon;
        this.cname = cname;
    }

    public City(String cname) {
        this.cname = cname;
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

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city1 = (City) o;
        return Objects.equals(lat, city1.lat) && Objects.equals(lon, city1.lon) && Objects.equals(cname, city1.cname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lon, cname);
    }

    @Override
    public String toString() {
        return "City{" +
                "lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", city='" + cname + '\'' +
                '}';
    }
}
