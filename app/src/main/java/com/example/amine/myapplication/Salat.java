package com.example.amine.myapplication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Salat {
    private SalatName salatName = null;
    private String startAt = null;
    private String endtAt = null;
    private EtatSalat etatSalat = null;
    private Calendar cal;
    int Score = 2;

    public Salat(SalatName salatName, EtatSalat etatSalat) {
        this.salatName = salatName;
        this.etatSalat = etatSalat;
// إدخال أوقات الصلاة ==============================
        switch (salatName) {
            case FAJR: {
                cal = Calendar.getInstance();
                cal.set(Calendar.HOUR_OF_DAY, 5);
                cal.set(Calendar.MINUTE, 22);
                break;
            }
            case DHUHR: {
                cal = Calendar.getInstance();
                cal.set(Calendar.HOUR_OF_DAY, 12);
                cal.set(Calendar.MINUTE, 44);
                break;
            }
            case ASR: {
                cal = Calendar.getInstance();
                cal.set(Calendar.HOUR_OF_DAY, 16);
                cal.set(Calendar.MINUTE, 8);
                break;
            }
            case MAGHRIB: {
                cal = Calendar.getInstance();
                cal.set(Calendar.HOUR_OF_DAY, 18);
                cal.set(Calendar.MINUTE, 45);
                break;
            }
            case ISHA: {
                cal = Calendar.getInstance();
                cal.set(Calendar.HOUR_OF_DAY, 20);
                cal.set(Calendar.MINUTE, 02);
                break;
            }
        }

        Date date = cal.getTime();


        DateFormat dateFormat = new SimpleDateFormat("HH:mm");

        String formattedDate = dateFormat.format(date);
        this.startAt = formattedDate ;
        this.endtAt = null;
    }

    public SalatName getSalatName() {
        return salatName;
    }

    public void setSalatName(SalatName salatName) {
        this.salatName = salatName;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getEndtAt() {
        return endtAt;
    }

    public void setEndtAt(String endtAt) {
        this.endtAt = endtAt;
    }

    public EtatSalat getEtatSalat() {
        return etatSalat;
    }

    public void setEtatSalat(EtatSalat etatSalat) {
        switch (etatSalat){
            case COLLECTIVE: {
                Score = 2;
                break;
            }
            case INDIVIDUAL:{
                Score = 1;
                break;
            }
            case OUT_OF_TIME:{
                Score = 0;
                break;
            }
        }
        this.etatSalat = etatSalat;
    }
}
