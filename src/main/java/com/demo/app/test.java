package com.demo.app;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class test {

    public static void main(String args[]){

        long unixTime = Instant.now().getEpochSecond()*1000;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");
        String date = dateFormat.format(unixTime);
        System.out.println(date);

    }
}
