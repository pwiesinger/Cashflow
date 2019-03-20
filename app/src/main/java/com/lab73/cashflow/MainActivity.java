package com.lab73.cashflow;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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
        verwendungszwecke.setAdapter(FileManager.getVerwendungszweckAdapter(getApplicationContext()));


        // configuring date picker
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

        // configuring number picker
        numberPicker.setOnClickListener(view -> showNumberPicker(view));


    }


    private void okButtonPressed(View view) {
        if (!logic.addEntryToStore(getApplicationContext())) {
            Toast.makeText(getApplicationContext(), "Daten sind nicht vollständig", Toast.LENGTH_LONG).show();
        }
    }

    private void showNumberPicker(View view) {
        NumberPicker picker = new NumberPicker(getApplicationContext());
        picker.setMinValue(0);
        picker.setMaxValue(99);

        NumberPicker pickerT = new NumberPicker(getApplicationContext());
        pickerT.setMinValue(0);
        pickerT.setMaxValue(10000);


        TextView dot = new TextView(getApplicationContext());
        dot.setText(",");
        dot.setTextSize(50);
        dot.setPadding(50,0,50,0);
        /*FrameLayout layout = new FrameLayout(getApplicationContext());
        layout.addView(picker, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER));*/

        LinearLayout layout = new LinearLayout(getApplicationContext());
        layout.setGravity(Gravity.CENTER);
        layout.addView(pickerT);
        layout.addView(dot);
        layout.addView(picker);



        new AlertDialog.Builder(this)
                .setView(layout)
                .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
                    // do something with picker.getValue()
                    int a = picker.getValue();
                    int a1 = pickerT.getValue();
                    logic.setPickerValues(a, a1);
                })
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }
}
