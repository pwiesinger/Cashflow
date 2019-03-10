package com.lab73.cashflow;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String verwendungszweckFilename = "verwendungszwecke.csv";

    public static List<String> getVerwendungszwecke(Context context) {

        List<String> list = new ArrayList<>();

        try {
            InputStream is = context.getAssets().open(verwendungszweckFilename);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = br.readLine();
            while (line != null) {
                list.add(line);
                line = br.readLine();
            }
            br.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
        try {
            FileInputStream fis = context.openFileInput(verwendungszweckFilename);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = br.readLine();
            while(line != null) {
                list.add(line);
                line = br.readLine();
            }
            br.close();
        } catch (IOException io) {
            // the folder does not exist yet.
        }
        return list;
    }

    public static void addEntryToVerwendungszwecke(String verwendungszweck, Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(verwendungszweckFilename, Context.MODE_APPEND);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos), true);
            pw.println(verwendungszweck);
            pw.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public static ArrayAdapter getEinnahmeAusgabeAdapter(Context context) {
        String[] array = new String[]{"- Ausgaben", "+ Einnahmen"};
        return new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, array);
    }

    public static ArrayAdapter getVerwendungszweckAdapter(Context context) {
        List<String> list = getVerwendungszwecke(context);
        return new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, list.toArray());
    }
}
