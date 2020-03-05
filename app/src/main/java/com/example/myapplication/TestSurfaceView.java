package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

public class TestSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    MyThread myThread;

    public TestSurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        myThread = new MyThread(getContext(), getHolder());
        myThread.run();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        myThread.stopRequest();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        myThread.changeXY(event.getX(), event.getY());
        return false;
    }
}
