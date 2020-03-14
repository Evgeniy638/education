package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyCompanyAdapter extends ArrayAdapter<MyCompany> {
    public MyCompanyAdapter(@NonNull Context context, @NonNull MyCompany[] objects) {
        super(context, R.layout.adapter_item, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        MyCompany myCompany = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.adapter_item, null);
        }

        if (myCompany != null) {
            ((ImageView)convertView.findViewById(R.id.logo))
                    .setImageResource(myCompany.picture);
            ((TextView)convertView.findViewById(R.id.name)).setText(myCompany.name);
            ((TextView)convertView.findViewById(R.id.cost)).setText(myCompany.cost + "$");
        }

        return convertView;
    }
}
