package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends Activity {
    Button button;
    ImageView imageView;
    ProgressBar progressBar;
    Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.download);
        imageView = findViewById(R.id.img);
        progressBar = findViewById(R.id.progress_bar);

        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("HandlerLeak")
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                button.setEnabled(false);

                handler = new Handler(){
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);

                        imageView.setImageBitmap((Bitmap) msg.obj);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                };

                MyThread myThread = new MyThread();
                myThread.start();
            }
        });
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            super.run();

            try {
                sleep(5000);

                URL url = new URL("https://st.depositphotos.com/1020482/3088/i/450/depositphotos_" +
                        "30884913-stock-photo-android-posing-in-sunglasses.jpg");

                Bitmap bitmap = BitmapFactory.decodeStream((InputStream) url.getContent());

                Message msg = Message.obtain();

                msg.what = 0;
                msg.obj = bitmap;

                handler.sendMessage(msg);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
