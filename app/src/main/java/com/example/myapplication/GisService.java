package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
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
                URL url = new URL("http://icomms.ru/inf/meteo.php?tid=35");

                Scanner in = new Scanner((InputStream) url.getContent());

                result = "{\"gis\":" + in.nextLine() + "}";
            } catch (Exception e) {
                result = "не удалось загруть информацию о погоду";
            }

            return result;
        }
    }
}
