package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Adapter extends ArrayAdapter {

    Activity activity;
    List<Model> list;

    public Adapter(Activity activity, List<Model> list) {
        super(activity, R.layout.custom_layout, list);
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = activity.getLayoutInflater();
        convertView = layoutInflater.inflate(R.layout.custom_layout, null,true);

        TextView textViewName = convertView.findViewById(R.id.txt_name);
        TextView textViewEmail = convertView.findViewById(R.id.txt_email);
        TextView textViewContact = convertView.findViewById(R.id.txt_contact);

        Model model = list.get(position);

        textViewName.setText(model.getName());
        textViewEmail.setText(model.getEmail());
        textViewContact.setText(model.getContact());


        return convertView;
    }
}
