package com.lab73.cashflow;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Logic {

    public Entry currentEntry;
    public List<Entry> entries = new ArrayList<>();
    private EntryListAdapter entryListAdapter;
    private Context mContext;

    public Logic(Context context) {
        mContext = context;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(mContext.openFileInput("Entries.csv")));
            String line = br.readLine();
            while (line != null) {
                if (line.equals("")) break;
                entries.add(new Entry(line));
                line = br.readLine();
            }
        } catch(IOException io) {
            io.printStackTrace();
        }
        currentEntry = new Entry();
        entryListAdapter = new EntryListAdapter(context, R.layout.entry_list_layout, entries);
    }

    public boolean addEntryToStore(Context context) {
        try{
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(mContext.openFileOutput("Entries.csv", Context.MODE_APPEND)));
            if (!currentEntry.canBeAdded()) return false;
            //bw.newLine();
            bw.write(currentEntry.toString());
            bw.newLine();
            bw.flush();
            entries.add(currentEntry);
            entryListAdapter.notifyDataSetChanged();
            currentEntry = new Entry();
            return true;
        }catch(IOException io) {
            io.printStackTrace();
            return false;
        }
    }

    public void updateDateOfEntry(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        currentEntry.setCalendar(c);
    }

    public void setVerwendungszweck(String verwendungszweck) {
        currentEntry.setVerwendungszweck(verwendungszweck);
        if (!FileManager.getVerwendungszwecke(mContext).contains(verwendungszweck)) {
            FileManager.addEntryToVerwendungszwecke(verwendungszweck, mContext);
        }
    }

    public void setPickerValues(int a, int a1) {
        double d = Double.valueOf(""+a+"."+a1);
        currentEntry.setBetrag(d);
    }

    public EntryListAdapter getAdapter() {
        return entryListAdapter;
    }

    public void setAusEin(char AusEin) {
        if (AusEin == '-' && currentEntry.getBetrag() > 0) {
            currentEntry.setBetrag(currentEntry.getBetrag()*-1);
        } else if (currentEntry.getBetrag() < 0) {
            currentEntry.setBetrag(currentEntry.getBetrag()*-1);
        }
    }
}
