package com.example.myfirstapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

class CelebrityListAdapter extends ArrayAdapter<Celebrity> {

    public CelebrityListAdapter(Context context, List<Celebrity> celebrities) {
        super(context, 0, celebrities);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }
        Celebrity celebrity = getItem(position);
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(celebrity.getName());
        return convertView;
    }
}