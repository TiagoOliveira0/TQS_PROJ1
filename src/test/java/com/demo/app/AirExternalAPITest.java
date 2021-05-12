package com.demo.app;

import io.restassured.RestAssured;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AirExternalAPITest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @Test
    void whenGetAirqualityNow_thenReturnAirQuality( ){
        City c1 = new City("40.528484","-8.7655525","Aveiro");

        when().
                get("/AirQuality/now?city={city}", c1.getCity()).
                then().
                statusCode(200)
                .body(
                        "lat",equalTo( Float.parseFloat(c1.getLat())),
                        "lon", equalTo( Float.parseFloat(c1.getLon())),
                        "size()", is(11)
                );

    }

    @Test
    void whenGetAirqualityNext_thenReturnAirQuality( ) throws Exception {
        City c1 = new City("40.528484","-8.7655525","Aveiro");


        when().
                get("/AirQuality/next?city={city}", c1.getCity()).
                then().
                statusCode(200)
                .body(
                        "[0].lat",equalTo( Float.parseFloat(c1.getLat())),
                        "[0].lon", equalTo( Float.parseFloat(c1.getLon())),
                        "[0].size()", is(11),

                        "[1].lat",equalTo( Float.parseFloat(c1.getLat())),
                        "[1].lon", equalTo( Float.parseFloat(c1.getLon())),
                        "[1].size()", is(11),

                        "[2].lat",equalTo( Float.parseFloat(c1.getLat())),
                        "[2].lon", equalTo( Float.parseFloat(c1.getLon())),
                        "[2].size()", is(11),

                        "[3].lat",equalTo( Float.parseFloat(c1.getLat())),
                        "[3].lon", equalTo( Float.parseFloat(c1.getLon())),
                        "[3].size()", is(11),

                        "[4].lat",equalTo( Float.parseFloat(c1.getLat())),
                        "[4].lon", equalTo( Float.parseFloat(c1.getLon())),
                        "[4].size()", is(11)
                );

    }

    @Test
    void whenGetAirqualityLast_thenReturnAirQuality( ) throws Exception {
        City c1 = new City("40.528484","-8.7655525","Aveiro");

        when().
                get("/AirQuality/last?city={city}", c1.getCity()).
                then().
                statusCode(200)
                .body(
                        "[0].lat",equalTo( Float.parseFloat(c1.getLat())),
                        "[0].lon", equalTo( Float.parseFloat(c1.getLon())),
                        "[0].size()", is(11),

                        "[1].lat",equalTo( Float.parseFloat(c1.getLat())),
                        "[1].lon", equalTo( Float.parseFloat(c1.getLon())),
                        "[1].size()", is(11),

                        "[2].lat",equalTo( Float.parseFloat(c1.getLat())),
                        "[2].lon", equalTo( Float.parseFloat(c1.getLon())),
                        "[2].size()", is(11),

                        "[3].lat",equalTo( Float.parseFloat(c1.getLat())),
                        "[3].lon", equalTo( Float.parseFloat(c1.getLon())),
                        "[3].size()", is(11),

                        "[4].lat",equalTo( Float.parseFloat(c1.getLat())),
                        "[4].lon", equalTo( Float.parseFloat(c1.getLon())),
                        "[4].size()", is(11)
                );

    }
}
