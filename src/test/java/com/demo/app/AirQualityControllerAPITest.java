package com.demo.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes=AppApplication.class)
@AutoConfigureMockMvc
public class AirQualityControllerAPITest {

    @Autowired
    private MockMvc mvc;

    @Test
    void GetRootTest() throws Exception {
        mvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("HomePage"));
    }

    @Test
    void GetAirNowTest() throws Exception {
        mvc.perform(get("/now").param("city","Aveiro")).andExpect(status().isOk()).andExpect(view().name("AirQualityNow"));
    }

    @Test
    void GetAirNextTest() throws Exception {
        mvc.perform(get("/next").param("city","Aveiro")).andExpect(status().isOk()).andExpect(view().name("AirQualityNext"));
    }

    @Test
    void GetAirLastTest() throws Exception {
        mvc.perform(get("/last").param("city","Aveiro")).andExpect(status().isOk()).andExpect(view().name("AirQualityLast"));
    }

    @Test
    void GetCacheTest() throws Exception {
        mvc.perform(get("/cache")).andExpect(status().isOk());
    }

    @Test
    void GetGeoTest() throws Exception {
        mvc.perform(get("/AirQuality/geo").param("city","Aveiro")).andExpect(status().isOk());
    }
}
