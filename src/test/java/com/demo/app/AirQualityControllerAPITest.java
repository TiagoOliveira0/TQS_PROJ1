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
public class AirQualityControllerAPITest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @Test
    public void whenGetAirqualityNow_thenReturnAirQuality( ){
        City c1 = new City("40.528484","-8.7655525","Aveiro");

        long unixTime = Instant.now().getEpochSecond();
        Date dte = new Date((long) (unixTime*1000));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");
        String date = dateFormat.format(dte);

        when().
                get("/AirQuality/now?city={city}", c1.getCity()).
                then().
                statusCode(200)
                .body(
                        "lat",equalTo( Float.parseFloat(c1.getLat())),
                        "lon", equalTo( Float.parseFloat(c1.getLon())),
                        "date", equalTo(date),
                        "size()", is(11)
                );

    }

    @Test
    public void whenGetAirqualityNext_thenReturnAirQuality( ) throws Exception {
        City c1 = new City("40.528484","-8.7655525","Aveiro");

        long unixTime = Instant.now().getEpochSecond();
        Date dte = new Date((long) (unixTime*1000));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
        String mydate = dateFormat.format(dte);

        LocalDate date = LocalDate.parse(mydate);

        LocalDate datenext = date.plusDays(1);

        String[] parts = datenext.toString().split("-");

        String date1 = parts[2] + "-" + parts[1] + "-" + parts[0];


        when().
                get("/AirQuality/next?city={city}", c1.getCity()).
                then().
                statusCode(200)
                .body(
                        "[0].lat",equalTo( Float.parseFloat(c1.getLat())),
                        "[0].lon", equalTo( Float.parseFloat(c1.getLon())),
                        "[0].date", equalTo(date1),
                        "[0].size()", is(11)

                );

        datenext = datenext.plusDays(1);

        parts = datenext.toString().split("-");

        date1 = parts[2] + "-" + parts[1] + "-" + parts[0];

        when().
                get("/AirQuality/next?city={city}", c1.getCity()).
                then().
                statusCode(200)
                .body(
                "[1].lat",equalTo( Float.parseFloat(c1.getLat())),
                        "[1].lon", equalTo( Float.parseFloat(c1.getLon())),
                        "[1].date", equalTo(date1),
                        "[1].size()", is(11)
                );

        datenext = datenext.plusDays(1);

        parts = datenext.toString().split("-");

        date1 = parts[2] + "-" + parts[1] + "-" + parts[0];

        when().
                get("/AirQuality/next?city={city}", c1.getCity()).
                then().
                statusCode(200)
                .body(
                "[2].lat",equalTo( Float.parseFloat(c1.getLat())),
                "[2].lon", equalTo( Float.parseFloat(c1.getLon())),
                "[2].date", equalTo(date1),
                "[2].size()", is(11)
                );

        datenext = datenext.plusDays(1);

        parts = datenext.toString().split("-");

        date1 = parts[2] + "-" + parts[1] + "-" + parts[0];

        when().
                get("/AirQuality/next?city={city}", c1.getCity()).
                then().
                statusCode(200)
                .body(
                "[3].lat",equalTo( Float.parseFloat(c1.getLat())),
                "[3].lon", equalTo( Float.parseFloat(c1.getLon())),
                "[3].date", equalTo(date1),
                "[3].size()", is(11)
                );

        datenext = datenext.plusDays(1);

        parts = datenext.toString().split("-");

        date1 = parts[2] + "-" + parts[1] + "-" + parts[0];

        when().
                get("/AirQuality/next?city={city}", c1.getCity()).
                then().
                statusCode(200)
                .body(
                "[4].lat",equalTo( Float.parseFloat(c1.getLat())),
                "[4].lon", equalTo( Float.parseFloat(c1.getLon())),
                "[4].date", equalTo(date1),
                "[4].size()", is(11)
                );

    }

    @Test
    public void whenGetAirqualityLast_thenReturnAirQuality( ) throws Exception {
        City c1 = new City("40.528484","-8.7655525","Aveiro");

        long unixTime = Instant.now().getEpochSecond();
        Date dte = new Date((long) (unixTime*1000));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
        String mydate = dateFormat.format(dte);

        LocalDate date = LocalDate.parse(mydate);

        LocalDate datenext = date.minusDays(4);

        String[] parts = datenext.toString().split("-");

        String date1 = parts[2] + "-" + parts[1] + "-" + parts[0];


        when().
                get("/AirQuality/last?city={city}", c1.getCity()).
                then().
                statusCode(200)
                .body(
                        "[0].lat",equalTo( Float.parseFloat(c1.getLat())),
                        "[0].lon", equalTo( Float.parseFloat(c1.getLon())),
                        "[0].date", equalTo(date1),
                        "[0].size()", is(11)

                );

        date = LocalDate.parse(mydate);

        datenext = datenext.plusDays(1);

        parts = datenext.toString().split("-");

        date1 = parts[2] + "-" + parts[1] + "-" + parts[0];

        when().
                get("/AirQuality/last?city={city}", c1.getCity()).
                then().
                statusCode(200)
                .body(
                        "[1].lat",equalTo( Float.parseFloat(c1.getLat())),
                        "[1].lon", equalTo( Float.parseFloat(c1.getLon())),
                        "[1].date", equalTo(date1),
                        "[1].size()", is(11)
                );

        datenext = datenext.plusDays(1);

        parts = datenext.toString().split("-");

        date1 = parts[2] + "-" + parts[1] + "-" + parts[0];

        when().
                get("/AirQuality/last?city={city}", c1.getCity()).
                then().
                statusCode(200)
                .body(
                        "[2].lat",equalTo( Float.parseFloat(c1.getLat())),
                        "[2].lon", equalTo( Float.parseFloat(c1.getLon())),
                        "[2].date", equalTo(date1),
                        "[2].size()", is(11)
                );

        datenext = datenext.plusDays(1);

        parts = datenext.toString().split("-");

        date1 = parts[2] + "-" + parts[1] + "-" + parts[0];

        when().
                get("/AirQuality/last?city={city}", c1.getCity()).
                then().
                statusCode(200)
                .body(
                        "[3].lat",equalTo( Float.parseFloat(c1.getLat())),
                        "[3].lon", equalTo( Float.parseFloat(c1.getLon())),
                        "[3].date", equalTo(date1),
                        "[3].size()", is(11)
                );

        datenext = datenext.plusDays(1);

        parts = datenext.toString().split("-");

        date1 = parts[2] + "-" + parts[1] + "-" + parts[0];

        when().
                get("/AirQuality/last?city={city}", c1.getCity()).
                then().
                statusCode(200)
                .body(
                        "[4].lat",equalTo( Float.parseFloat(c1.getLat())),
                        "[4].lon", equalTo( Float.parseFloat(c1.getLon())),
                        "[4].date", equalTo(date1),
                        "[4].size()", is(11)
                );

    }
}
