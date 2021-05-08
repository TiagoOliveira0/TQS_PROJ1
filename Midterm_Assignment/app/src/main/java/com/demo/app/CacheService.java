package com.demo.app;

import java.util.List;
import java.util.Map;

public interface CacheService {

    public boolean add(Map<City, List<Air>> e);

    public boolean containsCity(String city);

    public boolean containsInfo(String city);

    public List<Air> getValue(String city);
}
