package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridLayout;

public class MainActivity extends Activity implements OnClickListener{

    private static int WIDTH = 6;
    private static int HEIGHT = 10;

    private static Button[][] cells;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cells);
        makeCells();

        generate();

    }

    public static void generate() {
        int number = 0;

        for (int i = 0; i < HEIGHT; i++){
            for (int j = 0; j < WIDTH; j++) {
                cells[i][j].setText(number + "");
            }

            number++;
        }
    }

    @Override
    public void onClick(View v) {
        Button tappedCell = (Button) v;

        int tappedX = getX(tappedCell);
        int tappedY = getY(tappedCell);

        for(int y = 0; y < HEIGHT; y++){
            cells[y][tappedX].setBackgroundColor(Color.RED);
        }

        for(int x = 0; x < WIDTH; x++){
            cells[tappedY][x].setBackgroundColor(Color.RED);
        }

    }

    int getX(View v) {
        return Integer.parseInt(((String) v.getTag()).split(",")[1]);
    }

    int getY(View v) {
        return Integer.parseInt(((String) v.getTag()).split(",")[0]);
    }

    void makeCells() {
        cells = new Button[HEIGHT][WIDTH];
        GridLayout cellsLayout = findViewById(R.id.CellsLayout);
        cellsLayout.removeAllViews();
        cellsLayout.setColumnCount(WIDTH);
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                cells[i][j] = (Button) inflater.inflate(R.layout.cell, cellsLayout, false);
                cells[i][j].setOnClickListener(this);
                cells[i][j].setTag(i + "," + j);
                cellsLayout.addView(cells[i][j]);
            }
    }

}
