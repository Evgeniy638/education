package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.GridLayout;

import ru.samsung.itschool.book.cells.R;

public class MainActivity extends Activity implements OnClickListener,
        OnLongClickListener {

    private int WIDTH = 4;
    private int HEIGHT = 6;

    private Button[][] cells;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cells);
        makeCells();

        generate();

    }

    void generate() {
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++)
                if (Math.random() >= 0.5)
                {
                    cells[i][j].setBackgroundColor(Color.BLACK);
                }
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    @Override
    public void onClick(View v) {
        Button tappedCell = (Button) v;

        //Получаем координтаты нажатой клетки
        int tappedX = getX(tappedCell);
        int tappedY = getY(tappedCell);

        if(((ColorDrawable)cells[tappedY][tappedX].getBackground()).getColor() == Color.BLACK){
            cells[tappedY][tappedX].setBackgroundColor(Color.WHITE);
        }else {
            cells[tappedY][tappedX].setBackgroundColor(Color.BLACK);
        }

        for(int i = 0; i < HEIGHT; i++){
            if (i == tappedY) continue;

            int color = ((ColorDrawable)cells[i][tappedX].getBackground()).getColor();

            if(color == Color.BLACK){
                cells[i][tappedX].setBackgroundColor(Color.WHITE);
            }else {
                cells[i][tappedX].setBackgroundColor(Color.BLACK);
            }
        }

        for(int i = 0; i < WIDTH; i++){
            if (i == tappedX) continue;

            int color = ((ColorDrawable)cells[tappedY][i].getBackground()).getColor();

            if(color == Color.BLACK){
                cells[tappedY][i].setBackgroundColor(Color.WHITE);
            }else {
                cells[tappedY][i].setBackgroundColor(Color.BLACK);
            }
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
        GridLayout cellsLayout = (GridLayout) findViewById(R.id.CellsLayout);
        cellsLayout.removeAllViews();
        cellsLayout.setColumnCount(WIDTH);
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                cells[i][j] = (Button) inflater.inflate(R.layout.cell, cellsLayout, false);
                cells[i][j].setOnClickListener(this);
                cells[i][j].setOnLongClickListener(this);
                cells[i][j].setTag(i + "," + j);
                cellsLayout.addView(cells[i][j]);
            }
    }

}
