package com.example.amine.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;


public class MainActivity extends Activity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCheckBox();
        button = findViewById(R.id.button3);

        button.setOnClickListener((View view) -> {
            button.setText(Tools.getCurrentTimeUsingCalendar());
        });

    }

    private void initCheckBox() {
        CheckBox fajr1 = (CheckBox) findViewById(R.id.RBFajr1);
        CheckBox fajr2 = (CheckBox) findViewById(R.id.RBFajr2);
        CheckBox fajr3 = (CheckBox) findViewById(R.id.RBFajr3);
        //////////////////////////////
        CheckBox dhuhr1 = (CheckBox) findViewById(R.id.RBDhuhr1);
        CheckBox dhuhr2 = (CheckBox) findViewById(R.id.RBDhuhr2);
        CheckBox dhuhr3 = (CheckBox) findViewById(R.id.RBDhuhr3);
        //////////////////////////////
        CheckBox asr1 = (CheckBox) findViewById(R.id.RBAsr1);
        CheckBox asr2 = (CheckBox) findViewById(R.id.RBAsr2);
        CheckBox asr3 = (CheckBox) findViewById(R.id.RBAsr3);
        ///////////////////////////////
        CheckBox maghrib1 = (CheckBox) findViewById(R.id.RBMaghrib1);
        CheckBox maghrib2 = (CheckBox) findViewById(R.id.RBMaghrib2);
        CheckBox maghrib3 = (CheckBox) findViewById(R.id.RBMaghrib3);
        ////////////////////////////
        CheckBox isha1 = (CheckBox) findViewById(R.id.RBIsha1);
        CheckBox isha2 = (CheckBox) findViewById(R.id.RBIsha2);
        CheckBox isha3 = (CheckBox) findViewById(R.id.RBIsha3);
//=============================================
        fajr1.setOnClickListener(view -> {
            fajr1.setChecked(true);
            fajr2.setChecked(false);
            fajr3.setChecked(false);
        });
        fajr2.setOnClickListener(view -> {
            fajr2.setChecked(true);
            fajr1.setChecked(false);
            fajr3.setChecked(false);
        });
        fajr3.setOnClickListener(view -> {
            fajr3.setChecked(true);
            fajr1.setChecked(false);
            fajr2.setChecked(false);
        });
//=============================================
        dhuhr1.setOnClickListener(View ->{
                dhuhr1.setChecked(true);
                dhuhr2.setChecked(false);
                dhuhr3.setChecked(false);
        });
        dhuhr2.setOnClickListener(view -> {
            dhuhr2.setChecked(true);
            dhuhr1.setChecked(false);
            dhuhr3.setChecked(false);
        });
        dhuhr3.setOnClickListener(view -> {
            dhuhr3.setChecked(true);
            dhuhr1.setChecked(false);
            dhuhr2.setChecked(false);
        });
//=============================================
        asr1.setOnClickListener(View ->{
            asr1.setChecked(true);
            asr2.setChecked(false);
            asr3.setChecked(false);
        });
        asr2.setOnClickListener(view -> {
            asr2.setChecked(true);
            asr3.setChecked(false);
            asr1.setChecked(false);
        });
        asr3.setOnClickListener(view -> {
            asr3.setChecked(true);
            asr2.setChecked(false);
            asr1.setChecked(false);
        });
//==============================================
        maghrib1.setOnClickListener(View ->{
            maghrib1.setChecked(true);
            maghrib2.setChecked(false);
            maghrib3.setChecked(false);
        });
        maghrib2.setOnClickListener(view -> {
            maghrib2.setChecked(true);
            maghrib1.setChecked(false);
            maghrib3.setChecked(false);
        });
        maghrib3.setOnClickListener(view -> {
            maghrib3.setChecked(true);
            maghrib1.setChecked(false);
            maghrib2.setChecked(false);
        });
//==============================================
        isha1.setOnClickListener(View ->{
            isha1.setChecked(true);
            isha2.setChecked(false);
            isha3.setChecked(false);
        });
        isha2.setOnClickListener(view -> {
            isha2.setChecked(true);
            isha1.setChecked(false);
            isha3.setChecked(false);
        });
        isha3.setOnClickListener(view -> {
            isha3.setChecked(true);
            isha1.setChecked(false);
            isha2.setChecked(false);
        });
    }
}
