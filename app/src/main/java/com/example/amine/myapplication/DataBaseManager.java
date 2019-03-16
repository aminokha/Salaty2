package com.example.amine.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseManager extends SQLiteOpenHelper {
    private static final String DataBaseName = "base.db";
    private static int DATA_BASE_VERSION = 1;
    private SQLiteDatabase database;


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

    long updateDayInDB(Day day) throws SQLiteConstraintException {
        database = this.getWritableDatabase();
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


}
