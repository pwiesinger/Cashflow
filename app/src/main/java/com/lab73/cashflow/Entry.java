package com.lab73.cashflow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Entry {
    private Calendar calendar = null;
    private double betrag = 0;
    private String verwendungszweck = null;

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public double getBetrag() {
        return betrag;
    }

    public void setBetrag(double betrag) {
        this.betrag = betrag;
    }

    public String getVerwendungszweck() {
        return verwendungszweck;
    }

    public void setVerwendungszweck(String verwendungszweck) {
        this.verwendungszweck = verwendungszweck;
    }

    public String getFormattedDate() {
        Date d = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(d);
    }

    public Entry(String csvLine) {
        String[] rawArray = csvLine.split(";");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(rawArray[0]));
        } catch (ParseException p) {
            p.printStackTrace();
        }

        betrag = Double.parseDouble(rawArray[1]);
        verwendungszweck = rawArray[2];
    }

    public Entry() {

    }

    public Entry(Calendar calendar, double betrag, char eingabeAusgabe, String verwendungszweck) {
        this.calendar = calendar;
        this.betrag = betrag;
        this.verwendungszweck = verwendungszweck;
    }

    @Override
    public String toString() {
        return getFormattedDate() + ";" + betrag + ";" + verwendungszweck;
    }

    public boolean canBeAdded() {
        if (verwendungszweck != null && betrag != 0 && calendar != null) return true;
        return false;
    }
}
