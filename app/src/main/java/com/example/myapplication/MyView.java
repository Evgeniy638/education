package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    Paint paint = new Paint();
    Path path = new Path();
    Path path2 = new Path();

    float xe, ye;
    float x, y, h = 0.1f;
    int k = 100;
    float xmin = -2 * (float)Math.PI, xmax = 2 * (float)Math.PI;

    @Override
    protected void onDraw(Canvas canvas) {

        int x0 = this.getWidth() / 2;
        int y0 = this.getHeight() / 2;

        paint.setStrokeWidth(5);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(30.0f);

        canvas.drawLine(x0, 0, x0, this.getHeight(), paint);
        canvas.drawText("Y", this.getWidth() / 2 + 10, 35, paint);
        canvas.drawLine(0, y0, this.getWidth(), y0, paint);
        canvas.drawText("X", this.getWidth() - 50, this.getHeight() / 2 + 50, paint);

        paint.setColor(Color.RED);
        for (x = xmin; x < xmax; x += h) {
            y = (float)Math.abs(Math.tan(x));

            xe = x0 + k * x;
            ye = y0 - k * y;

            float yPrev = (float)Math.abs(Math.tan(x - h));
            if (x == xmin || (y < 0 && yPrev > 0)) {
                path.moveTo(xe, ye);
            } else {
                path.lineTo(xe, ye);
            }

            canvas.drawPath(path, paint);
        }

        paint.setColor(Color.BLUE);
        for (x = xmin; x < xmax; x += h) {
            y = 3 * (float)-Math.sin(Math.abs(x));

            xe = x0 + k * x;
            ye = y0 - k * y;

            if (x == xmin) {
                path2.moveTo(xe, ye);
            } else {
                path2.lineTo(xe, ye);
            }

            canvas.drawPath(path2, paint);
        }
    }
}
