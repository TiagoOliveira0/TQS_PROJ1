package com.demo.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class CacheUnitTest {

    private Cache cache;


    @BeforeEach
    void setUp() {
        cache = new Cache(1000000000,1000000000);
    }

    @AfterEach
    void tearDown() {
        cache = null;
    }

    @Test
    void isEmpty() {
        assertTrue( cache.isEmpty(), "Empty stack should be empty");
    }

    @Test
    void add() {
        assertTrue( cache.isEmpty(), "Empty stack should be empty");
        City c1 = new City("Aveiro");
        Air a1 = new Air("",0,0,0,0,0,0);
        a1.setSo2(0);
        a1.setPm25(0);
        a1.setPm10(0);
        a1.setNh3(0);
        List<Air> lista = new ArrayList<>();
        lista.add(a1);
        Map<City, List<Air>> map = new HashMap<>();
        map.put(c1,lista);
        cache.add(map);

        assertFalse( cache.isEmpty(), "Now that we added something, cache should not be empty");
    }

    @Test
    void size() {
        assertTrue( cache.isEmpty(), "Empty stack should be empty");
        assertEquals(0,cache.size());

        City c1 = new City("Aveiro");
        Air a1 = new Air("",0,0,0,0,0,0);
        a1.setSo2(0);
        a1.setPm25(0);
        a1.setPm10(0);
        a1.setNh3(0);
        List<Air> lista = new ArrayList<>();
        lista.add(a1);
        Map<City, List<Air>> map = new HashMap<>();
        map.put(c1,lista);
        cache.add(map);

        assertEquals(1,cache.size());

        City c2 = new City("Porto");
        Map<City, List<Air>> map2 = new HashMap<>();
        map2.put(c2,lista);
        cache.add(map2);

        City c3 = new City("Coimbra");
        Map<City, List<Air>> map3 = new HashMap<>();
        map3.put(c3,lista);
        cache.add(map3);

        assertEquals(3,cache.size());
    }

    @Test
    void containsCity() {
        assertTrue( cache.isEmpty(), "Empty stack should be empty");
        assertFalse( cache.containsCity("Aveiro"), "We did not add Aveiro, cache should not have it");
        City c1 = new City("Aveiro");
        Air a1 = new Air("",0,0,0,0,0,0);
        a1.setSo2(0);
        a1.setPm25(0);
        a1.setPm10(0);
        a1.setNh3(0);
        List<Air> lista = new ArrayList<>();
        lista.add(a1);
        Map<City, List<Air>> map = new HashMap<>();
        map.put(c1,lista);
        cache.add(map);

        assertTrue( cache.containsCity("Aveiro"), "Now that we added Aveiro, cache should have it");
    }

    @Test
    void containsInfo() {
        assertTrue( cache.isEmpty(), "Empty stack should be empty");
        assertFalse( cache.containsCity("Aveiro"), "We did not add Aveiro, cache should not have it");
        assertFalse( cache.containsInfo("Aveiro"), "We did not add Aveiro, cache should not have Aveiro info");
        City c1 = new City("Aveiro");
        Air a1 = new Air("",0,0,0,0,0,0);
        a1.setSo2(0);
        a1.setPm25(0);
        a1.setPm10(0);
        a1.setNh3(0);
        List<Air> lista = new ArrayList<>();
        Map<City, List<Air>> map = new HashMap<>();
        map.put(c1,lista);
        cache.add(map);

        assertTrue( cache.containsCity("Aveiro"), "We add Aveiro, cache should not have it");
        assertFalse( cache.containsInfo("Aveiro"), "We did not add Aveiro info, cache should not have Aveiro info");

        lista.add(a1);
        map.put(c1,lista);
        cache.add(map);

        assertTrue( cache.containsInfo("Aveiro"), "We added Aveiro info, cache should have Aveiro info");
    }

    @Test
    void getValue() {
        assertTrue( cache.isEmpty(), "Empty stack should be empty");
        City c1 = new City("Aveiro");
        Air a1 = new Air("",0,0,0,0,0,0);
        a1.setSo2(0);
        a1.setPm25(0);
        a1.setPm10(0);
        a1.setNh3(0);
        List<Air> lista = new ArrayList<>();
        lista.add(a1);
        Map<City, List<Air>> map = new HashMap<>();
        map.put(c1,lista);
        cache.add(map);

        assertEquals(cache.getValue("Aveiro"),lista);
    }

    @Test
    void getCity() {
        assertTrue( cache.isEmpty(), "Empty stack should be empty");
        City c1 = new City("Aveiro");
        Air a1 = new Air("",0,0,0,0,0,0);
        a1.setSo2(0);
        a1.setPm25(0);
        a1.setPm10(0);
        a1.setNh3(0);
        List<Air> lista = new ArrayList<>();
        lista.add(a1);
        Map<City, List<Air>> map = new HashMap<>();
        map.put(c1,lista);
        cache.add(map);

        assertEquals(cache.getCity("Aveiro"), c1);
    }

    @Test
    void timetolive() {
        assertTrue( cache.isEmpty(), "Empty stack should be empty");
        City c1 = new City("Aveiro");
        Air a1 = new Air("",0,0,0,0,0,0);
        a1.setSo2(0);
        a1.setPm25(0);
        a1.setPm10(0);
        a1.setNh3(0);
        List<Air> lista = new ArrayList<>();
        lista.add(a1);
        Map<City, List<Air>> map = new HashMap<>();
        map.put(c1,lista);
        cache.add(map);

        assertTrue(cache.containsCity("Aveiro"));
        assertTrue(cache.containsInfo("Aveiro"));

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertFalse(cache.containsCity("Aveiro"), "Time to live expired so Aveiro cache should not have Aveiro anymore");
        assertFalse(cache.containsInfo("Aveiro"), "Time to live expired so Aveiro cache should not have Aveiro info anymore");


    }
}
