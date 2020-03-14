package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class MyThread extends Thread {
    private SurfaceHolder surfaceHolder;

    private boolean running = true;
    private float x = 0;
    private float y = 0;
    private int r = 0;

    MyThread(Context context, SurfaceHolder surfaceHolder){
        this.surfaceHolder = surfaceHolder;
    }

    public void changeXY(float x, float y){
        this.x = x;
        this.y = y;
        r = 0;
    }

    public void stopRequest(){
        running = false;
    }

    @Override
    public void run() {
        while (running){
            Canvas canvas = surfaceHolder.lockCanvas();
            Paint p = new Paint();

            try {
                canvas.drawColor(Color.BLUE);

                p.setColor(Color.YELLOW);
                r += 5;
                canvas.drawCircle(x, y, r, p);
            }finally {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }

            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
