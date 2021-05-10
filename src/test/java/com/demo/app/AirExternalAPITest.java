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
public class AirExternalAPITest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void GetRootTest() throws Exception {
        mvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("HomePage"));
    }

    @Test
    public void GetAirNowTest() throws Exception {
        mvc.perform(get("/now").param("city","Aveiro")).andExpect(status().isOk()).andExpect(view().name("AirQualityNow"));
    }

    @Test
    public void GetAirNextTest() throws Exception {
        mvc.perform(get("/next").param("city","Aveiro")).andExpect(status().isOk()).andExpect(view().name("AirQualityNext"));
    }

    @Test
    public void GetAirLastTest() throws Exception {
        mvc.perform(get("/last").param("city","Aveiro")).andExpect(status().isOk()).andExpect(view().name("AirQualityLast"));
    }

    @Test
    public void GetCacheTest() throws Exception {
        mvc.perform(get("/cache")).andExpect(status().isOk());
    }

    @Test
    public void GetGeoTest() throws Exception {
        mvc.perform(get("/AirQuality/geo").param("city","Aveiro")).andExpect(status().isOk());
    }
}
