package com.example.amine.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DataBaseManager extends SQLiteOpenHelper {
    private static final String DataBaseName = "base.db";
    private static int DATA_BASE_VERSION = 1;
    static int fajrColl = 0, fajrIndvd = 0, fajrOutOfTime = 0;
    static int dhuhrColl = 0, dhuhrIndvd = 0, dhuhrOutOfTime = 0;
    static int asrColl = 0, asrIndvd = 0, asrOutOfTime = 0;
    static int maghribColl = 0, maghribIndvd = 0, maghribOutOfTime = 0;
    static int ishaColl = 0, ishaIndvd = 0, ishaOutOfTime = 0;
    static double fajrPourcentage = 0.0, dhuhrPourcentage = 0, asrPourcentage = 0, maghribPourcentage = 0, ishaPourcentage = 0;
    static String firstDate;
    static String lastDate;
    static private SQLiteDatabase database;

    {
        getData();
    }
    public DataBaseManager(Context context) {
        super(context, DataBaseName, null, DATA_BASE_VERSION);
        Log.i("DB_MANAGEMENT", "database ouverte");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table days(" +
                " date Date not null primary key, " +
                SalatName.FAJR + " integer," +
                SalatName.DHUHR + " integer," +
                SalatName.ASR + " integer," +
                SalatName.MAGHRIB + " integer," +
                SalatName.ISHA + " integer );";
        db.execSQL(sql);
        Log.i("DB_MANAGEMENT", "database creer");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    long insertDayToDB(Day day) throws SQLiteConstraintException {
        database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Date", Tools.getCurrentDateUsingCalendar());
        values.put(SalatName.FAJR.toString(), day.Fajr.Score);
        values.put(SalatName.DHUHR.toString(), day.Dhuhr.Score);
        values.put(SalatName.ASR.toString(), day.Asr.Score);
        values.put(SalatName.MAGHRIB.toString(), day.Maghrib.Score);
        values.put(SalatName.ISHA.toString(), day.Isha.Score);
        long nbe = 0;

        return nbe = database.insertOrThrow("days", null, values);
    }

    static long getNbrOfDays(String first, String last) {
        Date firstDate = null;
        Date lastDate = null;
        long nbrDays = 0;
        try {
            firstDate = new SimpleDateFormat("yyyy/MM/dd").parse(first);
            lastDate = new SimpleDateFormat("yyyy/MM/dd").parse(last);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long diff = lastDate.getTime() - firstDate.getTime();
        if (firstDate.compareTo(lastDate) < 0) {
            nbrDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;

        } else if (firstDate.compareTo(lastDate) == 0) {
            nbrDays = 1;
        } else {
            try {
                throw new Exception("the first date is greater then the last date");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return nbrDays;
    }

    static double getFajrPourcentageTotal(String first, String last) {
        long nbrOfSalawat = getNbrOfDays(first, last);
        Log.i("nbrofdays: ", "nbr of salawat " + String.valueOf(nbrOfSalawat));
        Log.i("nbrofdays: ", "fajr coll " + String.valueOf(fajrColl));
        Log.i("nbrofdays: ", "fajr out " + String.valueOf(fajrOutOfTime));
        double result = (fajrColl - fajrOutOfTime) * 100 / nbrOfSalawat;
        Log.i("nbrofdays: ", "result =" + String.valueOf(result));
        Log.i("nbrofdays: ", "================================================");
        return result;
    }

    static double getDhuhrPourcentageTotal(String first, String last) {
        long nbrOfSalawat = getNbrOfDays(first, last);
        Log.i("nbrofdays: ", "nbr of salawat " + String.valueOf(nbrOfSalawat));
        Log.i("nbrofdays: ", "dhuhr coll " + String.valueOf(fajrColl));
        Log.i("nbrofdays: ", "dhuhr out " + String.valueOf(fajrOutOfTime));
        double result = (dhuhrColl - dhuhrOutOfTime) * 100 / nbrOfSalawat;
        Log.i("nbrofdays: ", "result =" + String.valueOf(result));
        Log.i("nbrofdays: ", "================================================");
        return result;
    }

    static double getAsrPourcentageTotal(String first, String last) {
        long nbrOfSalawat = getNbrOfDays(first, last);
        Log.i("nbrofdays: ", "nbr of salawat " + String.valueOf(nbrOfSalawat));
        Log.i("nbrofdays: ", "asr coll " + String.valueOf(fajrColl));
        Log.i("nbrofdays: ", "asr out " + String.valueOf(fajrOutOfTime));
        double result = (asrColl - asrOutOfTime) * 100 / nbrOfSalawat;
        Log.i("nbrofdays: ", "result =" + String.valueOf(result));
        Log.i("nbrofdays: ", "================================================");
        return result;
    }

    static double getMaghribPourcentageTotal(String first, String last) {
        long nbrOfSalawat = getNbrOfDays(first, last);
        Log.i("nbrofdays: ", "nbr of salawat " + String.valueOf(nbrOfSalawat));
        Log.i("nbrofdays: ", "maghrib coll " + String.valueOf(fajrColl));
        Log.i("nbrofdays: ", "maghrib out " + String.valueOf(fajrOutOfTime));
        double result = (maghribColl - maghribOutOfTime) * 100 / nbrOfSalawat;
        Log.i("nbrofdays: ", "result =" + String.valueOf(result));
        Log.i("nbrofdays: ", "================================================");
        return result;
    }

    static double getIshaPourcentageTotal(String first, String last) {
        long nbrOfSalawat = getNbrOfDays(first, last);
        Log.i("nbrofdays: ", "nbr of salawat " + String.valueOf(nbrOfSalawat));
        Log.i("nbrofdays: ", "isha coll " + String.valueOf(fajrColl));
        Log.i("nbrofdays: ", "isha out " + String.valueOf(fajrOutOfTime));
        double result = (ishaColl - ishaOutOfTime) * 100 / nbrOfSalawat;
        Log.i("nbrofdays: ", "result =" + String.valueOf(result));
        Log.i("nbrofdays: ", "================================================");
        return result;
    }

    long updateDayInDB(Day day) throws SQLiteConstraintException {
        database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Date", Tools.getCurrentDateUsingCalendar());
        values.put(SalatName.FAJR.toString(), day.Fajr.Score);
        values.put(SalatName.DHUHR.toString(), day.Dhuhr.Score);
        values.put(SalatName.ASR.toString(), day.Asr.Score);
        values.put(SalatName.MAGHRIB.toString(), day.Maghrib.Score);
        values.put(SalatName.ISHA.toString(), day.Isha.Score);
        long nbe = 0;

        return nbe = database.update("days", values, "date=?", new String[]{day.CurrentDate});
    }

    List<Day> getData() {
        database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from days", null);
        List<Day> dayList = new ArrayList<>();
        fajrColl = 0;
        Log.i("nbrof", "fajr coll is set to " + String.valueOf(fajrColl));
        fajrIndvd = 0;
        fajrOutOfTime = 0;
        dhuhrColl = 0;
        dhuhrIndvd = 0;
        dhuhrOutOfTime = 0;
        asrColl = 0;
        asrIndvd = 0;
        asrOutOfTime = 0;
        maghribColl = 0;
        maghribIndvd = 0;
        maghribOutOfTime = 0;
        ishaColl = 0;
        ishaIndvd = 0;
        ishaOutOfTime = 0;
        if (cursor.moveToFirst()) {
            firstDate = cursor.getString(0);
            cursor.moveToPrevious();
        }

        while (cursor.moveToNext()) {
            Salat fajr, dhuhr, asr, maghrib, isha;
            String date = cursor.getString(0);
            if (cursor.getString(1).equals("0")) {
                fajr = new Salat(SalatName.FAJR, EtatSalat.OUT_OF_TIME);
                fajrOutOfTime++;
            } else if (cursor.getString(1).equals("1")) {
                fajr = new Salat(SalatName.FAJR, EtatSalat.INDIVIDUAL);
                fajrIndvd++;
            } else {
                fajr = new Salat(SalatName.FAJR, EtatSalat.COLLECTIVE);
                fajrColl++;
            }
//=====================================================
            if (cursor.getString(2).equals("0")) {
                dhuhr = new Salat(SalatName.DHUHR, EtatSalat.OUT_OF_TIME);
                dhuhrOutOfTime++;
            } else if (cursor.getString(1).equals("1")) {
                dhuhr = new Salat(SalatName.DHUHR, EtatSalat.INDIVIDUAL);
                dhuhrIndvd++;
            } else {
                dhuhr = new Salat(SalatName.DHUHR, EtatSalat.COLLECTIVE);
                dhuhrColl++;
            }
            //=====================================================
            if (cursor.getString(2).equals("0")) {
                asr = new Salat(SalatName.ASR, EtatSalat.OUT_OF_TIME);
                dhuhrOutOfTime++;
            } else if (cursor.getString(1).equals("1")) {
                asr = new Salat(SalatName.ASR, EtatSalat.INDIVIDUAL);
                asrIndvd++;
            } else {
                asr = new Salat(SalatName.ASR, EtatSalat.COLLECTIVE);
                asrColl++;
            }
//=====================================================
            if (cursor.getString(2).equals("0")) {
                maghrib = new Salat(SalatName.MAGHRIB, EtatSalat.OUT_OF_TIME);
                maghribOutOfTime++;
            } else if (cursor.getString(1).equals("1")) {
                maghrib = new Salat(SalatName.MAGHRIB, EtatSalat.INDIVIDUAL);
                maghribIndvd++;
            } else {
                maghrib = new Salat(SalatName.MAGHRIB, EtatSalat.COLLECTIVE);
                maghribColl++;
            }
//=====================================================
            if (cursor.getString(2).equals("0")) {
                isha = new Salat(SalatName.ISHA, EtatSalat.OUT_OF_TIME);
                ishaOutOfTime++;
            } else if (cursor.getString(1).equals("1")) {
                isha = new Salat(SalatName.ISHA, EtatSalat.INDIVIDUAL);
                ishaIndvd++;
            } else {
                isha = new Salat(SalatName.ISHA, EtatSalat.COLLECTIVE);
                ishaColl++;
            }

            dayList.add(new Day(date, fajr, dhuhr, asr, maghrib, isha));

        }
        return dayList;

    }

    List<Day> getData(String first, String last) {
        database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from days", null);
        List<Day> dayList = new ArrayList<>();
        fajrColl = 0;
        Log.i("nbrof", "fajr coll is set to " + String.valueOf(fajrColl));
        fajrIndvd = 0;
        fajrOutOfTime = 0;
        dhuhrColl = 0;
        dhuhrIndvd = 0;
        dhuhrOutOfTime = 0;
        asrColl = 0;
        asrIndvd = 0;
        asrOutOfTime = 0;
        maghribColl = 0;
        maghribIndvd = 0;
        maghribOutOfTime = 0;
        ishaColl = 0;
        ishaIndvd = 0;
        ishaOutOfTime = 0;
        if (cursor.moveToFirst()) {
            firstDate = cursor.getString(0);
            cursor.moveToPrevious();
        }
        long days = getNbrOfDays(first, last);
        while (cursor.moveToNext() && days != 0) {
            days--;
            Salat fajr, dhuhr, asr, maghrib, isha;
            String date = cursor.getString(0);
            if (cursor.getString(1).equals("0")) {
                fajr = new Salat(SalatName.FAJR, EtatSalat.OUT_OF_TIME);
                fajrOutOfTime++;
                Log.i("nbrof", "fajr out is incremented");
            } else if (cursor.getString(1).equals("1")) {
                fajr = new Salat(SalatName.FAJR, EtatSalat.INDIVIDUAL);
                fajrIndvd++;
                Log.i("nbrof", "fajr idv is incremented");
            } else {
                fajr = new Salat(SalatName.FAJR, EtatSalat.COLLECTIVE);
                fajrColl++;
                Log.i("nbrof", "fajr coll is incremented");
            }
//=====================================================
            if (cursor.getString(2).equals("0")) {
                dhuhr = new Salat(SalatName.DHUHR, EtatSalat.OUT_OF_TIME);
                dhuhrOutOfTime++;
            } else if (cursor.getString(2).equals("1")) {
                dhuhr = new Salat(SalatName.DHUHR, EtatSalat.INDIVIDUAL);
                dhuhrIndvd++;
            } else {
                dhuhr = new Salat(SalatName.DHUHR, EtatSalat.COLLECTIVE);
                dhuhrColl++;
            }
            //=====================================================
            if (cursor.getString(3).equals("0")) {
                asr = new Salat(SalatName.ASR, EtatSalat.OUT_OF_TIME);
                asrOutOfTime++;
            } else if (cursor.getString(3).equals("1")) {
                asr = new Salat(SalatName.ASR, EtatSalat.INDIVIDUAL);
                asrIndvd++;
            } else {
                asr = new Salat(SalatName.ASR, EtatSalat.COLLECTIVE);
                asrColl++;
            }
//=====================================================
            if (cursor.getString(4).equals("0")) {
                maghrib = new Salat(SalatName.MAGHRIB, EtatSalat.OUT_OF_TIME);
                maghribOutOfTime++;
            } else if (cursor.getString(4).equals("1")) {
                maghrib = new Salat(SalatName.MAGHRIB, EtatSalat.INDIVIDUAL);
                maghribIndvd++;
            } else {
                maghrib = new Salat(SalatName.MAGHRIB, EtatSalat.COLLECTIVE);
                maghribColl++;
            }
//=====================================================
            if (cursor.getString(5).equals("0")) {
                isha = new Salat(SalatName.ISHA, EtatSalat.OUT_OF_TIME);
                ishaOutOfTime++;
            } else if (cursor.getString(5).equals("1")) {
                isha = new Salat(SalatName.ISHA, EtatSalat.INDIVIDUAL);
                ishaIndvd++;
            } else {
                isha = new Salat(SalatName.ISHA, EtatSalat.COLLECTIVE);
                ishaColl++;
            }

            dayList.add(new Day(date, fajr, dhuhr, asr, maghrib, isha));

        }
        return dayList;

    }
}
