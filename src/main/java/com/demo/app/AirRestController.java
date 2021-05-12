package com.demo.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Controller
public class AirRestController {

    Logger logger = LoggerFactory.getLogger(AirRestController.class);

    private String s = "s ago.";

    private String nc = "No city was found.";

    private String err = "ErrorPage";

    private String info = "Coordinates for the requested place were accessed via cache.";

    @Autowired
    private AirQualityService airQualityService;

    @Autowired
    private AirQualityGeoLocationService airQualityGeoLocationService;

    private Cache cache1 = new Cache();

    private Cache cache2 = new Cache();

    private Cache cache3 = new Cache();

    @GetMapping("/")
    public String getHomePage(){
        logger.info("Home page was accessed.");
        return "HomePage";
    }

    @GetMapping(path="/now")
    public String getAirqualitybyLocation(@RequestParam String city, Model model ){
        City c = null;
        if(cache1.containsCity(city)){
            if(cache1.containsInfo(city)){
                logger.info("Air pollution about today for the requested place accessed via cache because it was already requested less then " + TimeUnit.SECONDS.convert(cache1.getAvg(), TimeUnit.NANOSECONDS) + s);
                model.addAttribute("air",cache1.getValue(city).get(0));
                return "AirQualityNow";
            }
            logger.info(info);
            c = cache1.getCity(city);
        }

        if(c==null)
            c = airQualityGeoLocationService.getCoordsByCity(city);

        if(city!=null && c!=null){
            Air var = airQualityService.getAirByNow(c);
            Map<City, List<Air>> res = new HashMap<>();
            List<Air> lista = new ArrayList<>();
            lista.add(var);
            res.put(c,lista);
            cache1.add(res);
            logger.info("AirQualityNow page was accessed.");
            logger.info("Air pollution about today for the requested place was cached for the next " + TimeUnit.SECONDS.convert(cache1.getAvg(), TimeUnit.NANOSECONDS) + "s.");
            model.addAttribute("air", var);
            return "AirQualityNow";
        }
        else{
            logger.error(nc);
            return err;
        }

    }

    @GetMapping(path="/next")
    public String getAirqualityNextDays(@RequestParam String city, Model model){
        City c = null;
        if(cache2.containsCity(city)){
            if(cache2.containsInfo(city)){
                logger.info("Air pollution about the next 5 days for the requested place accessed via cache because it was already requested less then " + TimeUnit.SECONDS.convert(cache1.getAvg(), TimeUnit.NANOSECONDS) + s);
                model.addAttribute("air", cache2.getValue(city));
                return "AirQualityNext";
            }
            logger.info(info);
            c = cache2.getCity(city);
        }

        if(c == null)
            c = airQualityGeoLocationService.getCoordsByCity(city);

        if(city!=null && c!=null){
            Map<City,List<Air>> res = new HashMap<>();
            logger.info("AirQualityNext page was accessed.");

            List<Air> lista = airQualityService.getAirNextDays(c);

            res.put(c,lista);
            cache2.add(res);
            logger.info("Air pollution about the next 5 days for the requested place was cached for the next " + TimeUnit.SECONDS.convert(cache2.getAvg(), TimeUnit.NANOSECONDS) + "s.");
            model.addAttribute("air", lista);
            return "AirQualityNext";
        }
        else{
            logger.error(nc);
            return err;
        }
    }

    @GetMapping(path="/last")
    public String getAirqualityLastDays(@RequestParam String city, Model model){
        City c = null;
        if(cache3.containsCity(city)){
            if(cache3.containsInfo(city)){
                logger.info("Air pollution about the last 5 days for the requested place accessed via cache because it was already requested less then " + TimeUnit.SECONDS.convert(cache1.getAvg(), TimeUnit.NANOSECONDS) + s);
                model.addAttribute("air",cache3.getValue(city));
                return "AirQualityLast";
            }
            logger.info(info);
            c = cache3.getCity(city);
        }

        if(c == null)
            c = airQualityGeoLocationService.getCoordsByCity(city);

        if(city!=null && c!=null){
            Map<City, List<Air>> res = new HashMap<>();
            List<Air> lista = airQualityService.getAirLastDays(c);
            res.put(c,lista);
            cache3.add(res);
            logger.info("Air pollution about the last 5 days for the requested place was cached for the next " + TimeUnit.SECONDS.convert(cache3.getAvg(), TimeUnit.NANOSECONDS) + "s.");
            logger.info("AirQualityLast page was accessed.");
            model.addAttribute("air", lista);
            return "AirQualityLast";
        }
        else{
            logger.error(nc);
            return err;
        }
    }

    @GetMapping(path="/AirQuality/now")
    public ResponseEntity<Air> getAirqualitybyLocationAPI(@RequestParam String city){
        City c = null;
        if(cache1.containsCity(city)){
            if(cache1.containsInfo(city)){
                logger.info("Air pollution about today for the requested place accessed via cache because it was already requested less then " + TimeUnit.SECONDS.convert(cache1.getAvg(), TimeUnit.NANOSECONDS) + s);
                return new ResponseEntity<>(cache1.getValue(city).get(0), HttpStatus.OK);
            }
            logger.info(info);
            c = cache1.getCity(city);
        }

        if(c == null)
            c = airQualityGeoLocationService.getCoordsByCity(city);

        if(city!=null && c!=null){
            Air var = airQualityService.getAirByNow(c);
            if(var!=null){
                Map<City, List<Air>> res = new HashMap<>();
                List<Air> lista = new ArrayList<>();
                lista.add(var);
                res.put(c,lista);
                cache1.add(res);
                logger.info("AirQuality/now API endpoint was accessed.");
                logger.info("Air pollution about today for the requested place was cached for the next " + TimeUnit.SECONDS.convert(cache1.getAvg(), TimeUnit.NANOSECONDS) + "s.");
                return new ResponseEntity<>(var,HttpStatus.OK);
            }
            else{
                logger.error("No air pollution data found.");
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
        }
        else {
            logger.error("No city found.");
            return new ResponseEntity<>(new Air("",0, 0, 0, 0, 0, 0, 0, 0, 0, 0), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(path="/AirQuality/next")
    public ResponseEntity<List<Air>> getAirqualityNextDaysAPI(@RequestParam String city){
        City  c = null;
        if(cache2.containsCity(city)){
            if(cache2.containsInfo(city)){
                logger.info("Air pollution about the next 5 days for the requested place accessed via cache because it was already requested less then " + TimeUnit.SECONDS.convert(cache1.getAvg(), TimeUnit.NANOSECONDS) + s);
                return new ResponseEntity<>(cache2.getValue(city),HttpStatus.OK);
            }
            logger.info(info);
            c = cache2.getCity(city);
        }

        if(c == null)
            c = airQualityGeoLocationService.getCoordsByCity(city);

        List<Air> air = null;

        if(city!=null && c!=null){
            air = airQualityService.getAirNextDays(c);
            if(!air.isEmpty()){
                Map<City,List<Air>> res = new HashMap<>();
                res.put(c,air);
                cache2.add(res);
                logger.info("AirQuality/next API endpoint was accessed.");
                logger.info("Air pollution about the next 5 days for the place requested was cached for the next " + TimeUnit.SECONDS.convert(cache2.getAvg(), TimeUnit.NANOSECONDS) + "s.");

                return new ResponseEntity<>(air,HttpStatus.OK);
            }
            else{
                logger.error("No air pollution data found.");
                return new ResponseEntity<>(air,HttpStatus.NOT_FOUND);
            }

        }
        else{
            logger.error(nc);
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(path="/AirQuality/last")
    public ResponseEntity<List<Air>> getAirqualityLastDaysAPI(@RequestParam String city){
        City c = null;
        if(cache3.containsCity(city)){
            if(cache3.containsInfo(city)){
                logger.info("Air pollution about the last 5 days for the requested place accessed via cache because it was already requested less then " + TimeUnit.SECONDS.convert(cache1.getAvg(), TimeUnit.NANOSECONDS) + s);
                return new ResponseEntity<>(cache3.getValue(city),HttpStatus.OK);
            }
            logger.info(info);
            c = cache3.getCity(city);
        }

        if(c == null)
            c = airQualityGeoLocationService.getCoordsByCity(city);

        List<Air> air = null;

        if(city!=null){
            air = airQualityService.getAirLastDays(c);
            if(!air.isEmpty()){
                Map<City, List<Air>> res = new HashMap<>();
                res.put(c,air);
                cache3.add(res);
                logger.info("AirQuality/last API endpoint was accessed.");
                logger.info("Air pollution about the last 5 days for the requested place was cached for the next " + TimeUnit.SECONDS.convert(cache3.getAvg(), TimeUnit.NANOSECONDS) + "s.");
                return new ResponseEntity<>(air,HttpStatus.OK);
            }
            else{
                logger.error("There are no city or place for the request.");
                return new ResponseEntity<>(air,HttpStatus.NOT_FOUND);
            }
        }
        else{
            logger.error(nc);
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(path="/AirQuality/geo")
    public ResponseEntity<City> getGeoLocationbyCityAPI(@RequestParam String city){
        if(cache1.containsCity(city)){
            logger.info(info);
            return new ResponseEntity<>(cache1.getCity(city),HttpStatus.OK);
        }
        if(cache2.containsCity(city)){
            logger.info(info);
            return new ResponseEntity<>(cache2.getCity(city),HttpStatus.OK);
        }

        if(cache3.containsCity(city)){
            logger.info(info);
            return new ResponseEntity<>(cache3.getCity(city),HttpStatus.OK);
        }

        City var = airQualityGeoLocationService.getCoordsByCity(city);

        if(var == null){
            logger.error("There are no coordinates found fot city requested.");
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }


        List<Air> air = new ArrayList<>();
        Map<City,List<Air>> res = new HashMap<>();

        res.put(var,air);
        cache1.add(res);
        cache2.add(res);
        cache3.add(res);

        logger.info("AirQuality/geo API endpoint was accessed to request the coordinates a place.");
        logger.info("Coordinates for the requested place was cached for " + TimeUnit.SECONDS.convert(cache3.getAvg(), TimeUnit.NANOSECONDS) + "s.");
        return new ResponseEntity<>(var,HttpStatus.OK);
    }


    @GetMapping(path="/search")
    public RedirectView redirectSearch(
            RedirectAttributes attr,
            String service,
            String local
    ){
        if(local.equals("")){
            logger.warn("Tried to search for null location!");
            return new RedirectView("");
        }
        attr.addAttribute("city",local);

        switch(service) {

            case "Now":
                logger.info("Requested info about now.");
                return new RedirectView("now");

            case "Forecast":
                logger.info("Requested info about next.");
                return new RedirectView("next");

            case "Previous":
                logger.info("Requested info about last.");
                return new RedirectView("last");

            default:
                logger.info("No service found!");
                return new RedirectView("now");
        }
    }

    @GetMapping(path="/cache")
    public ResponseEntity<List<Cache>> getAirqualityCache(){
        List<Cache> caches = new ArrayList<>();
        caches.add(cache1);
        caches.add(cache2);
        caches.add(cache3);

        return new ResponseEntity<>(caches, HttpStatus.OK);

    }

}

