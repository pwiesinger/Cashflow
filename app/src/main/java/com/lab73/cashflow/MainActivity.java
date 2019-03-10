package com.lab73.cashflow;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button ok;
    private Spinner verwendungszwecke;
    private Spinner ausgabenEinnahmen;
    private Button datum;
    private Button numberPicker;
    private GridView gridView;
    private TextView cash;

    private Logic logic = new Logic();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ok = findViewById(R.id.ok);
        verwendungszwecke = findViewById(R.id.verwendungszweck);
        ausgabenEinnahmen = findViewById(R.id.ausgabenEinnahmen);
        datum = findViewById(R.id.datum);
        numberPicker = findViewById(R.id.euro);
        gridView = findViewById(R.id.gridView);
        cash = findViewById(R.id.cash);

        ok.setOnClickListener(view -> okButtonPressed(view));
        ausgabenEinnahmen.setAdapter(FileManager.getEinnahmeAusgabeAdapter(getApplicationContext()));


        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                logic.updateDateOfEntry(year, monthOfYear, dayOfMonth);
            }
        };

        datum.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH) ).show();
        });
    }


    private void okButtonPressed(View view) {

    }
}
