package com.example.amine.myapplication;

public class Day {
    String CurrentDate = null;
    Salat Fajr = null;
    Salat Dhuhr = null;
    Salat Asr = null;
    Salat Maghrib = null;
    Salat Isha = null;

    public Day(String currentDate, Salat fajr, Salat dhuhr, Salat asr, Salat maghrib, Salat isha) {
        CurrentDate = currentDate;
        Fajr = fajr;
        Dhuhr = dhuhr;
        Asr = asr;
        Maghrib = maghrib;
        Isha = isha;
    }
}
