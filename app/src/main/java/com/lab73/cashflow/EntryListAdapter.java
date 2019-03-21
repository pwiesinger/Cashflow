package com.lab73.cashflow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class EntryListAdapter extends ArrayAdapter<Entry> {

    private static final String TAG = "EntryListAdapter";
    private Context mContext;
    private int mResource;

    public EntryListAdapter( Context context, int resource, List<Entry> objects) {
        super(context, resource, objects);

        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        String datum = getItem(position).getCalendar().toString();
//        String cat = getItem(position).getVerwendungszweck();
//        String euro = "" + getItem(position).getEingabeAusgabe() + getItem(position).getBetrag();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView euro = convertView.findViewById(R.id.euroField);
        TextView date = convertView.findViewById(R.id.datum);
        TextView category = convertView.findViewById(R.id.category);

        euro.setText(""+getItem(position).getBetrag());
        date.setText(getItem(position).getFormattedDate());
        category.setText(getItem(position).getVerwendungszweck());


        return convertView;
    }
}
