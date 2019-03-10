package com.lab73.cashflow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button ok;
    private Spinner verwendungszwecke;
    private Spinner ausgabenEinnahmen;
    private TextView datum;
    private TextView numberPicker;
    private GridView gridView;
    private TextView cash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ok = findViewById(R.id.ok);
        verwendungszwecke = findViewById(R.id.verwendungszweck);
        ausgabenEinnahmen = findViewById(R.id.ausgabenEinnahmen);
        datum = findViewById(R.id.datum);
        numberPicker = findViewById(R.id.euro);
        gridView = findViewById(R.id.gridView);
        cash = findViewById(R.id.cash);

        
    }
}
