package com.demo.app;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class Cache implements CacheService{
    private long avg;
    private long count;
    private long created;
    private long max;
    private long min;
    private Map<Map<City, List<Air>>, Long> map = new HashMap<>();

    public Cache() {
        created = System.nanoTime();
        min = 10000000000L;
        max = 10000000000L;
        avg = (min + max) / 2;
    }


    public Cache(long min, long max) {
        created = System.nanoTime();
        this.min = min;
        this.max = max;
        avg = (min + max) / 2;
    }

    public boolean isEmpty(){
        onAccess();
        if(map.size()==0)
            return true;

        else
            return false;
    }

    public int size(){
        onAccess();
        return map.size();
    }

    public boolean add(Map<City, List<Air>> e) {
        City city = null;
        for (Iterator<Map.Entry<City, List<Air>>> it = e.entrySet().iterator(); it.hasNext();) {
            city = it.next().getKey();
        }

        boolean result = false;

        if(!this.containsCity(city.getCity()) && city!=null) {
            map.put(e, Long.valueOf(System.nanoTime()));
            if(this.containsCity(city.getCity()))
                result=true;
        }
        onAccess();
        return result;
    }

    public boolean containsCity(String city) {
        onAccess();
        for (Iterator<Map.Entry<Map<City, List<Air>>, Long>> it = map.entrySet().iterator(); it.hasNext();) {
            Map<City, List<Air>> resultados = it.next().getKey();
            for(City i: resultados.keySet()){
                if(i.getCity().equals(city))
                    return true;
            }
        }

        return false;
    }

    public boolean containsInfo(String city) {
        onAccess();
        for (Iterator<Map.Entry<Map<City, List<Air>>, Long>> it = map.entrySet().iterator(); it.hasNext();) {
            Map<City, List<Air>> resultados = it.next().getKey();
            for(City i: resultados.keySet()){
                if(i.getCity().equals(city) && resultados.get(i).size()!=0)
                    return true;
            }
        }

        return false;
    }

    public List<Air> getValue(String city){
        onAccess();
        if(this.containsCity(city) && this.containsInfo(city)){
            for (Iterator<Map.Entry<Map<City, List<Air>>, Long>> it = map.entrySet().iterator(); it.hasNext();) {
                Map<City, List<Air>> resultados = it.next().getKey();
                for(City i: resultados.keySet()){
                    if(i.getCity().equals(city))
                        return resultados.get(i);
                }
            }
        }
        return null;


    }

    public City getCity(String city){
        onAccess();
        if(this.containsCity(city)){
            for (Iterator<Map.Entry<Map<City, List<Air>>, Long>> it = map.entrySet().iterator(); it.hasNext();) {
                Map<City, List<Air>> resultados = it.next().getKey();
                for(City i: resultados.keySet()){
                    if(i.getCity().equals(city))
                        return i;
                }
            }
        }
        return null;
    }

    private void onAccess() {
        count++;
        long now = System.nanoTime();
        for (Iterator<Map.Entry<Map<City, List<Air>>, Long>> it = map.entrySet().iterator(); it.hasNext();) {
            long t = it.next().getValue();
            if (now > t + min && (now > t + max || now + (now - created) / count > t + avg)) {
                it.remove();
            }
        }
    }

    @Override
    public String toString() {
        onAccess();
        return "Cache{"  + map.toString() + "}";
    }

    public long getAvg() {
        onAccess();
        return avg;
    }

    public Map<Map<City, List<Air>>, Long> getMap(){
        onAccess();
        return map;
    }

}
