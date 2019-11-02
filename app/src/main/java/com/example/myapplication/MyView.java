package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyView extends View {
    public MyView(Context context) {
        super(context);

        for(int i = 0; i < N; i++){
            lastTime[i] = System.currentTimeMillis();
        }
    }

    int N = 10;
    float[] x  = new float[N];
    float[] y  = new float[N];
    float[] vx = new float[N];
    float[] vy = new float[N];
    int radius = 30;

    float minV = 0.3f;

    int[] colors = {
            Color.GREEN,
            Color.BLUE,
            Color.rgb(105, 0, 5),
            Color.rgb(64, 201, 87),
            Color.rgb(89, 3, 105),
            Color.RED,
            Color.rgb(255, 102, 0),
            Color.YELLOW,
            Color.rgb(50, 168, 147),
            Color.rgb(97, 50, 168)
    };

    long[] lastTime = new long[N];

    Paint paint = new Paint();

    boolean isStarted;

    @Override
    protected void onDraw(Canvas canvas) {
        ballsInitialization();

        addValues();

        checkBorder();
        crossingCheck();

        drawLine(canvas);
        drawCircle(canvas);

        invalidate();
    }

    protected void drawCircle(Canvas canvas){
        for (int i = 0; i < N; i++) {
            paint.setColor(colors[i]);
            canvas.drawCircle(x[i], y[i], radius, paint);
        }
    }

    protected void drawLine(Canvas canvas){
        for (int i = 0; i < N; i++) {
            paint.setStrokeWidth(3);
            paint.setColor(Color.GRAY);
            canvas.drawLine(x[i], y[i], x[(i + 1) % N], y[(i + 1) % N], paint);
        }
    }

    protected void crossingCheck(){
        for(int i = 0; i < N; i++){
            for(int j = i + 1; j < N; j++){
                float dx = x[i] - x[j];
                float dy = y[i] - y[j];

                if(Math.sqrt(dx * dx + dy * dy) <= radius * 2){
                    float kx = vx[i];
                    float ky = vy[i];

                    vx[i] = vx[j];
                    vy[i] = vy[j];

                    vx[j] = kx;
                    vy[j] = ky;
                }

                while (Math.sqrt(dx * dx + dy * dy) <= radius * 2){
                    x[i] += vx[i];
                    y[i] += vy[i];

                    x[j] += vx[j];
                    y[j] += vy[j];

                    dx = x[i] - x[j];
                    dy = y[i] - y[j];
                }
            }
        }
    }

    protected void checkBorder(){
        for (int i = 0; i < N; i++) {
            if(x[i] + radius >= getWidth()) vx[i] = -Math.abs(vx[i]);
            if(x[i] - radius <= 0) vx[i] = Math.abs(vx[i]);

            if(y[i] + radius >= getHeight()) vy[i] = -Math.abs(vy[i]);
            if(y[i] - radius <= 0) vy[i] = Math.abs(vy[i]);
        }
    }

    protected void addValues(){
        for (int i = 0; i < N; i++) {
            long nowTime = System.currentTimeMillis();

            x[i] += vx[i] * (nowTime - lastTime[i]);
            y[i] += vy[i] * (nowTime - lastTime[i]);

            lastTime[i] = nowTime;
        }
    }

    protected void ballsInitialization(){
        if(!isStarted){
            isStarted = true;

            for (int i = 0; i < N; i++) {
                x[i] = (float) (Math.random() * (getWidth() - 2 * radius) + radius);
                y[i] = (float) (Math.random() * (getWidth() - 2 * radius) + radius);
                vx[i] = (float)(Math.max(Math.random() * 4 * minV, minV) - 2 * minV);
                vy[i] = (float)(Math.max(Math.random() * 4 * minV, minV) - 2 * minV);
            }
        }
    }
}