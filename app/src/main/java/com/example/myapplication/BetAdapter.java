package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BetAdapter extends ArrayAdapter<Bet> {
    public BetAdapter(@NonNull Context context, @NonNull Bet[] objects) {
        super(context, R.layout.adapter_item, objects);
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Bet bet = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item, null);
        }

        if (bet != null) {
            ((TextView)convertView.findViewById(R.id.TeamHome)).setText(bet.teamHome);
            ((TextView)convertView.findViewById(R.id.TeamGuest)).setText(bet.teamGuest);
            ((TextView)convertView.findViewById(R.id.TeamBet))
                    .setText(bet.betGuestHome + " : " + bet.betGuestHome);
        }

        return convertView;
    }
}
