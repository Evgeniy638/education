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

import java.util.ArrayList;

public class BetAdapter extends ArrayAdapter<Bet> {
    ArrayList<Bet> bets;

    public BetAdapter(@NonNull Context context, @NonNull ArrayList<Bet> bets) {
        super(context, R.layout.item);
        setBets(bets);
    }

    public void setBets(ArrayList<Bet> bets){
        this.bets = bets;
    }

    @Nullable
    @Override
    public Bet getItem(int position) {
        return bets.get(getCount() - position - 1);
    }

    @Override
    public int getCount() {
        return bets.size();
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
            ((TextView)convertView.findViewById(R.id.TeamHome)).setText(bet.getTeamHome());
            ((TextView)convertView.findViewById(R.id.TeamGuest)).setText(bet.getTeamGuest());
            ((TextView)convertView.findViewById(R.id.TeamBet))
                    .setText(bet.getBetTeamHome() + " : " + bet.getBetTeamGuest());
        }

        return convertView;
    }
}
