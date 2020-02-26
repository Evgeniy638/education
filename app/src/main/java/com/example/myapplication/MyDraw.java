package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class MyDraw extends View {
    Paint paint = new Paint();
    float width;
    float height;

    public MyDraw(Context context) {
        super(context);
    }

    @SuppressLint("NewApi")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        width = getWidth();
        height = getHeight();

        drawBackgroundGrass(0, height * 0.767f, width, height, canvas);
        drawTree(width * 0.438f, height * 0.324f,
                width * 0.655f, height * 0.93f, canvas);
        drawBench(width * 0.742f, height * 0.8f,
                width * 0.941f, height * 0.874f, canvas);
        drawHouse(width * 0.127f, height * 0.392f,
                width * 0.378f, height * 0.907f, canvas);
    }

    private void drawHouse(float x0, float y0, float x1, float y1, Canvas canvas){
        //рисую крышу
        float[] xRoof = {
                x0,
                x0 + (x1 - x0) * 0.563f,
                x1
        };

        float[] yRoof = {
                y0 + (y1 - y0) * 0.4f,
                y0,
                y0 + (y1 - y0) * 0.4f
        };

        Path roof = new Path();
        roof.moveTo(xRoof[0], yRoof[0]);
        roof.lineTo(xRoof[1], yRoof[1]);
        roof.lineTo(xRoof[2], yRoof[2]);
        roof.lineTo(xRoof[0], yRoof[0]);

        paint.setColor(Color.rgb(128, 64, 0));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(roof, paint);

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        canvas.drawPath(roof, paint);

        //рисую окно на крыше
        float xWindow = x0 + (x1 - x0) * 0.563f;
        float yWindow = y0 + (y1 - y0) * 0.31f;
        float r = 0.033f * Math.min(width, height);

        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);

        float angle = (float) (Math.PI / 7);
        float j = -3 * angle;
        for (float i = -1 * angle; i <=  angle * 4; i += angle){
            canvas.drawLine(xWindow + r * (float) Math.cos(i),
                    yWindow + r * (float) Math.sin(i),
                    xWindow + r * (float) Math.cos(j),
                    yWindow + r * (float) Math.sin(j), paint);
            j -= angle;
        }

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        canvas.drawCircle(xWindow, yWindow, r, paint);

        //рисую основную часть дома

        float[] xMain = {
                x0,
                x1,
                x1,
                x0
        };

        float[] yMain = {
                y0 + (y1 - y0) * 0.4f,
                y0 + (y1 - y0) * 0.4f,
                y1,
                y1
        };

        Path main = new Path();
        main.moveTo(xMain[0], yMain[0]);
        main.lineTo(xMain[1], yMain[1]);
        main.lineTo(xMain[2], yMain[2]);
        main.lineTo(xMain[3], yMain[3]);
        main.lineTo(xMain[0], yMain[0]);

        paint.setColor(Color.rgb(128, 64, 0));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(main, paint);

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        canvas.drawPath(main, paint);

        //рисую окно на основной части дома
    }

    private void drawBench(float x0, float y0, float x1, float y1, Canvas canvas){
        final float heightBenchTop = 11f;
        final float widthBenchBottom = 15f;
        final float xBenchBottom1 = x0 + (x1 - x0) * 0.284f - widthBenchBottom / 2f;
        final float yBenchBottom = y0 + heightBenchTop;
        final float xBenchBottom2 =  x0 + (x1 - x0) * 0.715f - widthBenchBottom / 2f;

        paint.setColor(Color.rgb(128, 128, 128));
        paint.setStyle(Paint.Style.FILL);

        canvas.drawRect(x0, y0, x1, y0 + heightBenchTop, paint);
        canvas.drawRect(xBenchBottom1, yBenchBottom,
                xBenchBottom1 + widthBenchBottom, y1, paint);
        canvas.drawRect(xBenchBottom2, yBenchBottom,
                xBenchBottom2 + widthBenchBottom, y1, paint);

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);

        canvas.drawRect(x0, y0, x1, y0 + heightBenchTop, paint);
        canvas.drawRect(xBenchBottom1, yBenchBottom,
                xBenchBottom1 + widthBenchBottom, y1, paint);
        canvas.drawRect(xBenchBottom2, yBenchBottom,
                xBenchBottom2 + widthBenchBottom, y1, paint);
    }

    private void drawBackgroundGrass(float x0, float y0, float x1, float y1, Canvas canvas){
        paint.setColor(Color.rgb(0, 128, 0));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(x0, y0, x1, y1, paint);

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(x0, y0, x1, y0, paint);
    }

    @SuppressLint("NewApi")
    private void drawTree(float x0, float y0, float x1, float y1, Canvas canvas){
        float xTrunk0 = x0 + (x1 - x0) / 2f - 5;
        float yTrunk0 = y0 + (y1 - y0) * 0.91f;

        paint.setColor(Color.rgb(128, 64, 0));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(xTrunk0, yTrunk0, xTrunk0 + 10, y1, paint);

        paint.setColor(Color.rgb(0, 255, 0));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawOval(x0, y0, x1, yTrunk0, paint);

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        canvas.drawOval(x0, y0, x1, yTrunk0, paint);


    }
}
