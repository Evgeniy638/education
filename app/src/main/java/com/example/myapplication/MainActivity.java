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

        MyCompanyAdapter adapter = new MyCompanyAdapter(this, makeCompanies());
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    MyCompany[] makeCompanies(){
        MyCompany[] arr = new MyCompany[10];

        String[] names = {
                "Electronic Arts",
                "Яндекс",
                "Google",
                "Apple",
                "Facebook",
                "IBM",
                "Microsoft",
                "Сбребанк",
                "Oracle",
                "Blizzard"
        };

        int[] pictures = {
                R.drawable.electronic_arts,
                R.drawable.yandex,
                R.drawable.google,
                R.drawable.apple,
                R.drawable.facebook,
                R.drawable.ibm,
                R.drawable.microsoft,
                R.drawable.sberbank,
                R.drawable.oracle,
                R.drawable.blizzard
        };

        double[] costs = {
                97.06,
                33.94,
                1214.27,
                277.97,
                170.28,
                107.95,
                158.83,
                2.71,
                47.93,
                59.04
        };

        for (int i = 0; i < arr.length; i++){
            arr[i] = new MyCompany();
            arr[i].name = names[i];
            arr[i].picture = pictures[i];
            arr[i].cost = costs[i];
        }

        return arr;
    }
}
