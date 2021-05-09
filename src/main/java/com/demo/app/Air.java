package com.demo.app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Air {
    private float lat;
    private float lon;
    private float co;
    private float no;
    private float no2;
    private float o3;
    private float so2;
    private float pm2_5;
    private float pm10;
    private float nh3;
    private String date;


    public Air(String date,float lat, float lon, float co, float no, float no2, float o3, float so2, float pm2_5, float pm10, float nh3) {
        this.date = date;
        this.lat = lat;
        this.lon = lon;
        this.co = co;
        this.no = no;
        this.no2 = no2;
        this.o3 = o3;
        this.so2 = so2;
        this.pm2_5 = pm2_5;
        this.pm10 = pm10;
        this.nh3 = nh3;
    }



    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getCo() {
        return co;
    }

    public void setCo(float co) {
        this.co = co;
    }

    public float getNo() {
        return no;
    }

    public void setNo(float no) {
        this.no = no;
    }

    public float getNo2() {
        return no2;
    }

    public void setNo2(float no2) {
        this.no2 = no2;
    }

    public float getO3() {
        return o3;
    }

    public void setO3(float o3) {
        this.o3 = o3;
    }

    public float getSo2() {
        return so2;
    }

    public void setSo2(float so2) {
        this.so2 = so2;
    }

    public float getPm2_5() {
        return pm2_5;
    }

    public void setPm2_5(float pm2_5) {
        this.pm2_5 = pm2_5;
    }

    public float getPm10() {
        return pm10;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPm10(float pm10) {
        this.pm10 = pm10;
    }

    public float getNh3() {
        return nh3;
    }

    public void setNh3(float nh3) {
        this.nh3 = nh3;
    }


    @Override
    public String toString() {
        return "Air{" +
                "date=" + date +
                ", lat=" + lat +
                ", lon=" + lon +
                ", co=" + co +
                ", no=" + no +
                ", no2=" + no2 +
                ", o3=" + o3 +
                ", so2=" + so2 +
                ", pm2_5=" + pm2_5 +
                ", pm10=" + pm10 +
                ", nh3=" + nh3 +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Air air = (Air) o;
        return Float.compare(air.lat, lat) == 0 && Float.compare(air.lon, lon) == 0 && Float.compare(air.co, co) == 0 && Float.compare(air.no, no) == 0 && Float.compare(air.no2, no2) == 0 && Float.compare(air.o3, o3) == 0 && Float.compare(air.so2, so2) == 0 && Float.compare(air.pm2_5, pm2_5) == 0 && Float.compare(air.pm10, pm10) == 0 && Float.compare(air.nh3, nh3) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lon, co, no, no2, o3, so2, pm2_5, pm10, nh3);
    }

}
