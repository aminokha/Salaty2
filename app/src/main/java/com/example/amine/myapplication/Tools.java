package com.example.amine.myapplication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

//import java.time.LocalTime;

public final class Tools {


    public static void getCurrentTimeUsingDate() {

        Date date = new Date();

        String strDateFormat = "hh:mm:ss a";

        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);

        String formattedDate = dateFormat.format(date);

        System.out.println("Current time of the day using Date - 12 hour format: " + formattedDate);

    }

    public static String getCurrentTimeUsingCalendar() {

        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();

        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        String formattedDate = dateFormat.format(date);

        return formattedDate;

    }

    public static String getCurrentDateUsingCalendar() {

        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");


        String formattedDate = dateFormat.format(date);

        return formattedDate;

    }
}
