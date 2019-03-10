package com.lab73.cashflow;

import java.util.Calendar;

public class Entry {
    private Calendar calendar;
    private double betrag;
    private char eingabeAusgabe;
    private String verwendungszweck;

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

    public char getEingabeAusgabe() {
        return eingabeAusgabe;
    }

    public void setEingabeAusgabe(char eingabeAusgabe) {
        this.eingabeAusgabe = eingabeAusgabe;
    }

    public String getVerwendungszweck() {
        return verwendungszweck;
    }

    public void setVerwendungszweck(String verwendungszweck) {
        this.verwendungszweck = verwendungszweck;
    }
}
