package com.example.myapplication;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();

        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(getWidth(), 0, 0, getHeight(), paint);

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        float
                x = getWidth() / 2,
                y = getHeight() / 2,
                r = Math.min(getWidth(), getHeight()) / 3;
        
        canvas.drawCircle( x, y, r, paint);
    }
}
