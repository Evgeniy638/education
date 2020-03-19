package com.example.myapplication;


import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BetAdapter adapter = new BetAdapter(this, getBets());
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    private Bet[] getBets(){
        Bet[] bets = new Bet[10];
        return bets;
    }
}
