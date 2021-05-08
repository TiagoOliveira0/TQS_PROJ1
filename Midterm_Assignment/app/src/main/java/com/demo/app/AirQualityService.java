package com.demo.app;

import org.json.JSONException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AirQualityService {

    public Air getAirByNow(City c);

    public List<Air> getAirNextDays(City c);

    public List<Air> getAirLastDays(City c);
}
