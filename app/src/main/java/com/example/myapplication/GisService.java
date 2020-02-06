package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class GisService extends Service {

    public static final String CHANNEL = "GIS_SERVICE";
    public static final String INFO = "INFO";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "Service created", Toast.LENGTH_SHORT).show();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show();

        GisAsyncTask gisAsyncTask = new GisAsyncTask();
        gisAsyncTask.execute();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service destroyed", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    private class GisAsyncTask extends AsyncTask<Void, Void, String>{

        @Override
        protected void onPostExecute(String s) {
            Intent i = new Intent(CHANNEL);
            i.putExtra(INFO, s);
            sendBroadcast(i);
        }

        @Override
        protected String doInBackground(Void... voids) {
            String result;

            try {
                URL url = new URL("https://samples.openweathermap.org/data/2.5/weather?id=2172797&appid=b6907d289e10d714a6e88b30761fae22");

                Scanner in = new Scanner((InputStream) url.getContent());

                result = in.nextLine();
            } catch (Exception e) {
                result = "не удалось загруть информацию о погоде";
            }

            return result;
        }
    }
}
