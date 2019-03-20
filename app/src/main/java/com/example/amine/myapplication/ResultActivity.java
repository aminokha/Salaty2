package com.example.amine.myapplication;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.amine.myapplication.MainActivity.dataBaseManager;

public class ResultActivity extends AppCompatActivity {
    TextView dateStart, dateEnd, totalPouctg, fajrPourctg, dhuhrPourctg, asrPourctg, maghribPourctg, ishaPourctg;
    TextView lblfajrOut, lblfajrIdv, lblfajrColl, lblDhuhrOut, lblDhuhrIdv, lblDhuhrColl, lblAsrOut, lblAsrIdv, lblAsrColl,
            lblMaghribOut, lblMaghribIdv, lblMaghribColl, lblIshaOut, lblIshaIdv, lblIshaColl;
    private DatePickerDialog.OnDateSetListener onDateStartSet, onDateEndset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);
        dateStart = findViewById(R.id.dateStart);
        dateEnd = findViewById(R.id.dateEnd);
        totalPouctg = findViewById(R.id.totalPouctg);

        fajrPourctg = findViewById(R.id.fajrPourctg);
        dhuhrPourctg = findViewById(R.id.dhuhrPourctg);
        asrPourctg = findViewById(R.id.asrPourctg);
        maghribPourctg = findViewById(R.id.maghribPourctg);
        ishaPourctg = findViewById(R.id.ishaPourctg);

        lblfajrOut = findViewById(R.id.fajrOut);
        lblfajrIdv = findViewById(R.id.fajrIdv);
        lblfajrColl = findViewById(R.id.fajrColl);

        lblDhuhrOut = findViewById(R.id.dhuhrOut);
        lblDhuhrIdv = findViewById(R.id.dhuhrIdv);
        lblDhuhrColl = findViewById(R.id.dhuhrColl);

        lblAsrOut = findViewById(R.id.asrOut);
        lblAsrIdv = findViewById(R.id.asrIdv);
        lblAsrColl = findViewById(R.id.asrColl);

        lblMaghribOut = findViewById(R.id.maghribOut);
        lblMaghribIdv = findViewById(R.id.maghribIdv);
        lblMaghribColl = findViewById(R.id.maghribColl);

        lblIshaOut = findViewById(R.id.ishaOut);
        lblIshaIdv = findViewById(R.id.ishaIdv);
        lblIshaColl = findViewById(R.id.ishaColl);

///////////////////////////////////////////////////
        dateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ResultActivity.this,
                        onDateStartSet,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(255, 100, 181, 246)));

                Date minDate = null;
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    minDate = dateFormat.parse(DataBaseManager.firstDate);
                    Log.i("nbrof ", minDate.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                dialog.getDatePicker().setMinDate(minDate.getTime());
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.show();
            }
        });

        onDateStartSet = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = year + "/" + (month < 10 ? "0" + month : month) + "/" + (day < 10 ? "0" + day : day);
                dateStart.setText(date);
            }
        };
/////////////////////////////////////////////////////////////
        dateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ResultActivity.this,
                        onDateEndset,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(255, 100, 181, 246)));
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                Date minDate = null;
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    minDate = dateFormat.parse(dateStart.getText().toString());
                    Log.i("nbrof ", minDate.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                dialog.getDatePicker().setMinDate(minDate.getTime());

                dialog.show();
            }
        });
        onDateEndset = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = year + "/" + (month < 10 ? "0" + month : month) + "/" + (day < 10 ? "0" + day : day);
                dateEnd.setText(date);
                dataBaseManager.getData(dateStart.getText().toString(), dateEnd.getText().toString());
                int total = (int) ((setFajrView() + setDhuhrView() + setAsrView() + setMaghribView() + setIshaView()) / 5);

                totalPouctg.setText("% " + String.valueOf(total));

            }
        };
    }

    private double setFajrView() {
        double fajrPourcentage = DataBaseManager.getFajrPourcentageTotal(
                dateStart.getText().toString(), dateEnd.getText().toString());
        fajrPourctg.setText("% " + String.valueOf((int) fajrPourcentage));
        lblfajrColl.setText(String.valueOf(DataBaseManager.fajrColl));
        lblfajrIdv.setText(String.valueOf(DataBaseManager.fajrIndvd));
        lblfajrOut.setText(String.valueOf(DataBaseManager.fajrOutOfTime));
        return fajrPourcentage;
    }

    private double setDhuhrView() {
        double dhuhPourcentage = DataBaseManager.getDhuhrPourcentageTotal(
                dateStart.getText().toString(), dateEnd.getText().toString());
        dhuhrPourctg.setText("% " + String.valueOf((int) dhuhPourcentage));
        lblDhuhrColl.setText(String.valueOf(DataBaseManager.dhuhrColl));
        lblDhuhrIdv.setText(String.valueOf(DataBaseManager.dhuhrIndvd));
        lblDhuhrOut.setText(String.valueOf(DataBaseManager.dhuhrOutOfTime));
        return dhuhPourcentage;
    }

    private double setAsrView() {
        double asrPourcentage = DataBaseManager.getAsrPourcentageTotal(
                dateStart.getText().toString(), dateEnd.getText().toString());
        asrPourctg.setText("% " + String.valueOf((int) asrPourcentage));
        lblAsrColl.setText(String.valueOf(DataBaseManager.asrColl));
        lblAsrIdv.setText(String.valueOf(DataBaseManager.asrIndvd));
        lblAsrOut.setText(String.valueOf(DataBaseManager.asrOutOfTime));
        return asrPourcentage;
    }

    private double setMaghribView() {
        double maghribPourcentage = DataBaseManager.getMaghribPourcentageTotal(
                dateStart.getText().toString(), dateEnd.getText().toString());
        maghribPourctg.setText("% " + String.valueOf((int) maghribPourcentage));
        lblMaghribColl.setText(String.valueOf(DataBaseManager.maghribColl));
        lblMaghribIdv.setText(String.valueOf(DataBaseManager.maghribIndvd));
        lblMaghribOut.setText(String.valueOf(DataBaseManager.maghribOutOfTime));
        return maghribPourcentage;
    }

    private double setIshaView() {
        double ishaPourcentage = DataBaseManager.getIshaPourcentageTotal(
                dateStart.getText().toString(), dateEnd.getText().toString());
        ishaPourctg.setText("% " + String.valueOf((int) ishaPourcentage));
        lblIshaColl.setText(String.valueOf(DataBaseManager.ishaColl));
        lblIshaIdv.setText(String.valueOf(DataBaseManager.ishaIndvd));
        lblIshaOut.setText(String.valueOf(DataBaseManager.ishaOutOfTime));
        return ishaPourcentage;
    }

    public void toAccueil(View view) {
//        setContentView(R.layout.activity_main);

    }
}
