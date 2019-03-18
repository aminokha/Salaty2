package com.example.amine.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static SQLiteDatabase database;
    public Salat Fajr, Dhuhr, Asr, Maghrib, Isha;
    TextView actualDate;
    private Button btnSave;
    private DataBaseManager dataBaseManager;
    TextView totalPouctg, fajrPourctg, dhuhrpourctg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//=============================================================
        Fajr = new Salat(SalatName.FAJR, EtatSalat.COLLECTIVE);
        Dhuhr = new Salat(SalatName.FAJR, EtatSalat.COLLECTIVE);
        Asr = new Salat(SalatName.FAJR, EtatSalat.COLLECTIVE);
        Maghrib = new Salat(SalatName.FAJR, EtatSalat.COLLECTIVE);
        Isha = new Salat(SalatName.FAJR, EtatSalat.COLLECTIVE);
//=============================================================
        dataBaseManager = new DataBaseManager(this);
        setContentView(R.layout.activity_main);
        actualDate = (TextView) findViewById(R.id.actualDate);
        actualDate.setText(Tools.getCurrentDateUsingCalendar());
        initCheckBox();
        btnSave = (Button) findViewById(R.id.button3);
//        totalPouctg = (TextView) findViewById(R.id.totalPouctg);
//        fajrPourctg = (TextView) findViewById(R.id.fajrPourctg);
//        dhuhrpourctg = (TextView) findViewById(R.id.dhuhrpourctg);

        btnSave.setOnClickListener((View view) -> {
            saveOrUpdate(new Day(Tools.getCurrentDateUsingCalendar(), Fajr, Dhuhr, Asr, Maghrib, Isha));
        });

    }


    private void initCheckBox() {
        CheckBox ChBxFajrColl = (CheckBox) findViewById(R.id.RBFajr1);
        CheckBox ChBxFajrIdv = (CheckBox) findViewById(R.id.RBFajr2);
        CheckBox ChBxFajrHors = (CheckBox) findViewById(R.id.RBFajr3);
        //////////////////////////////
        CheckBox ChBxDhuhrColl = (CheckBox) findViewById(R.id.RBDhuhr1);
        CheckBox ChBxDhuhrIdv = (CheckBox) findViewById(R.id.RBDhuhr2);
        CheckBox ChBxDhuhrHors = (CheckBox) findViewById(R.id.RBDhuhr3);
        //////////////////////////////
        CheckBox ChBxAsrColl = (CheckBox) findViewById(R.id.RBAsr1);
        CheckBox ChBxAsrIdv = (CheckBox) findViewById(R.id.RBAsr2);
        CheckBox ChBxAsrHors = (CheckBox) findViewById(R.id.RBAsr3);
        ///////////////////////////////
        CheckBox ChBxMaghribColl = (CheckBox) findViewById(R.id.RBMaghrib1);
        CheckBox ChBxMaghribIdv = (CheckBox) findViewById(R.id.RBMaghrib2);
        CheckBox ChBxMaghribHors = (CheckBox) findViewById(R.id.RBMaghrib3);
        ////////////////////////////
        CheckBox ChBxIshaColl = (CheckBox) findViewById(R.id.RBIsha1);
        CheckBox ChBxIshaIdv = (CheckBox) findViewById(R.id.RBIsha2);
        CheckBox ChBxIshaHors = (CheckBox) findViewById(R.id.RBIsha3);
//=============================================
        ChBxFajrColl.setOnClickListener(view -> {
            ChBxFajrColl.setChecked(true);
            ChBxFajrIdv.setChecked(false);
            ChBxFajrHors.setChecked(false);
            Fajr.setEtatSalat(EtatSalat.COLLECTIVE);
        });
        ChBxFajrIdv.setOnClickListener(view -> {
            ChBxFajrIdv.setChecked(true);
            ChBxFajrColl.setChecked(false);
            ChBxFajrHors.setChecked(false);
            Fajr.setEtatSalat(EtatSalat.INDIVIDUAL);
        });
        ChBxFajrHors.setOnClickListener(view -> {
            ChBxFajrHors.setChecked(true);
            ChBxFajrColl.setChecked(false);
            ChBxFajrIdv.setChecked(false);
            Fajr.setEtatSalat(EtatSalat.OUT_OF_TIME);
        });
//=============================================
        ChBxDhuhrColl.setOnClickListener(View -> {
            ChBxDhuhrColl.setChecked(true);
            ChBxDhuhrIdv.setChecked(false);
            ChBxDhuhrHors.setChecked(false);
            Dhuhr.setEtatSalat(EtatSalat.COLLECTIVE);
        });
        ChBxDhuhrIdv.setOnClickListener(view -> {
            ChBxDhuhrIdv.setChecked(true);
            ChBxDhuhrColl.setChecked(false);
            ChBxDhuhrHors.setChecked(false);
            Dhuhr.setEtatSalat(EtatSalat.INDIVIDUAL);
        });
        ChBxDhuhrHors.setOnClickListener(view -> {
            ChBxDhuhrHors.setChecked(true);
            ChBxDhuhrColl.setChecked(false);
            ChBxDhuhrIdv.setChecked(false);
            Dhuhr.setEtatSalat(EtatSalat.OUT_OF_TIME);
        });
//=============================================
        ChBxAsrColl.setOnClickListener(View -> {
            ChBxAsrColl.setChecked(true);
            ChBxAsrIdv.setChecked(false);
            ChBxAsrHors.setChecked(false);
            Asr.setEtatSalat(EtatSalat.COLLECTIVE);
        });
        ChBxAsrIdv.setOnClickListener(view -> {
            ChBxAsrIdv.setChecked(true);
            ChBxAsrHors.setChecked(false);
            ChBxAsrColl.setChecked(false);
            Asr.setEtatSalat(EtatSalat.INDIVIDUAL);
        });
        ChBxAsrHors.setOnClickListener(view -> {
            ChBxAsrHors.setChecked(true);
            ChBxAsrIdv.setChecked(false);
            ChBxAsrColl.setChecked(false);
            Asr.setEtatSalat(EtatSalat.OUT_OF_TIME);
        });
//==============================================
        ChBxMaghribColl.setOnClickListener(View -> {
            ChBxMaghribColl.setChecked(true);
            ChBxMaghribIdv.setChecked(false);
            ChBxMaghribHors.setChecked(false);
            Maghrib.setEtatSalat(EtatSalat.COLLECTIVE);
        });
        ChBxMaghribIdv.setOnClickListener(view -> {
            ChBxMaghribIdv.setChecked(true);
            ChBxMaghribColl.setChecked(false);
            ChBxMaghribHors.setChecked(false);
            Maghrib.setEtatSalat(EtatSalat.INDIVIDUAL);
        });
        ChBxMaghribHors.setOnClickListener(view -> {
            ChBxMaghribHors.setChecked(true);
            ChBxMaghribColl.setChecked(false);
            ChBxMaghribIdv.setChecked(false);
            Maghrib.setEtatSalat(EtatSalat.OUT_OF_TIME);
        });
//==============================================
        ChBxIshaColl.setOnClickListener(View -> {
            ChBxIshaColl.setChecked(true);
            ChBxIshaIdv.setChecked(false);
            ChBxIshaHors.setChecked(false);
            Isha.setEtatSalat(EtatSalat.COLLECTIVE);
        });
        ChBxIshaIdv.setOnClickListener(view -> {
            ChBxIshaIdv.setChecked(true);
            ChBxIshaColl.setChecked(false);
            ChBxIshaHors.setChecked(false);
            Isha.setEtatSalat(EtatSalat.INDIVIDUAL);
        });
        ChBxIshaHors.setOnClickListener(view -> {
            ChBxIshaHors.setChecked(true);
            ChBxIshaColl.setChecked(false);
            ChBxIshaIdv.setChecked(false);
            Isha.setEtatSalat(EtatSalat.OUT_OF_TIME);
        });
    }

    private void saveOrUpdate(Day day) {
        long nbe = 0;
        try {
            nbe = dataBaseManager.insertDayToDB(day);
        } catch (SQLiteConstraintException ex) {
            Log.i("sqliteError", "repeted key");
            showMessageErr(day);
        }
        if (nbe > 0) {
            Toast.makeText(this, "تمت عملية الحفظ بنجاح", Toast.LENGTH_LONG).show();
        }
    }

    void showMessageErr(Day day) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("لقد قمت بتسجيل صلوات هذا اليوم سابقا !!!" + "\n هل تريد تعديل البيانات المسجلة ؟");
        builder.setPositiveButton("نعم", (dialog, id) -> {
            Log.i("click", "click to yes);");
            dataBaseManager.updateDayInDB(day);
            Toast.makeText(this, "تمت عملية التعديل بنجاح", Toast.LENGTH_LONG).show();
        });
        builder.setNegativeButton("لا", (dialog, id) -> {
            Log.i("click", "click to no);");
            Toast.makeText(this, "تم إلغاء التعديلات", Toast.LENGTH_LONG).show();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void toResult(View view) {
        setContentView(R.layout.result_layout);
    }

    public void toAccueil(View view) {
        setContentView(R.layout.activity_main);

    }
}
