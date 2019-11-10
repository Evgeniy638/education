package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.wifi.aware.WifiAwareManager;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.GridLayout;

public class MainActivity extends Activity implements OnClickListener,
        OnLongClickListener {

    private static int WIDTH = 6;
    private static int HEIGHT = 10;

    private static int amountBombs = 10;

    static boolean[][] bombs = new boolean[HEIGHT][WIDTH];
    static boolean[][] isOpenAllCells = new boolean[HEIGHT][WIDTH];
    static boolean[][] marks = new boolean[HEIGHT][WIDTH];

    int[] colorCell = {
            Color.BLUE,
            Color.rgb(95, 111, 227),
            Color.rgb(98, 171, 240),
            Color.rgb(97, 242, 250),
            Color.rgb(7, 227, 187),
            Color.rgb(224, 247, 10),
            Color.rgb(250, 101, 2),
            Color.rgb(255, 0, 0)

    };

    private static Button[][] cells;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cells);
        makeCells();

        generate();

    }

    public static void generate() {
        for (int i = 0; i < HEIGHT; i++){
            for (int j = 0; j < WIDTH; j++) {
                bombs[i][j] = false;
                isOpenAllCells[i][j] = false;
                marks[i][j] = false;

                cells[i][j].setBackgroundColor(Color.GRAY);
                cells[i][j].setText("");
            }
        }

        makeBombs();
    }

    private static void makeBombs(){
        int count = 0;

        while (count != amountBombs){
            int newY = (int)(Math.random() * HEIGHT);
            int newX = (int)(Math.random() * WIDTH);

            if(bombs[newY][newX]) continue;

            bombs[newY][newX] = true;
            count++;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        Button tappedCell = (Button) v;

        int tappedX = getX(tappedCell);
        int tappedY = getY(tappedCell);

        if(isOpenAllCells[tappedY][tappedX]) return false;

        if(!marks[tappedY][tappedX])
            putMark(tappedY, tappedX);
        else
            removeMark(tappedY, tappedX);

        return false;
    }

    @Override
    public void onClick(View v) {
        Button tappedCell = (Button) v;

        int tappedX = getX(tappedCell);
        int tappedY = getY(tappedCell);

        if (marks[tappedY][tappedX]) return;

        if(bombs[tappedY][tappedX]){
            cells[tappedY][tappedX].setBackgroundColor(Color.RED);
            lose();
        }else if(!isOpenAllCells[tappedY][tappedX]){
            openCell(tappedY, tappedX);
        }

        if(checkOpenAllNoBombsCells()){
            win();
        }

    }

    private void removeMark(int y, int x){
        cells[y][x].setBackgroundColor(Color.GRAY);
        marks[y][x] = false;
    }

    private void putMark(int y, int x){
        cells[y][x].setBackgroundColor(Color.YELLOW);
        marks[y][x] = true;
    }

    public void openCell(int y, int x){
        int countBombs = 0;

        int
                maxI = (y + 1 < HEIGHT) ?y + 1 :HEIGHT - 1,
                maxJ = (x + 1 < WIDTH) ?x + 1 :WIDTH - 1,
                minI = (y - 1 >= 0) ?y - 1 :0,
                minJ = (x - 1 >= 0) ?x - 1 :0;

        for (int i = minI; i <= maxI; i++){
            for (int j = minJ; j <= maxJ; j++){
                if (i == y && j == x) continue;

                if (bombs[i][j]){
                    countBombs++;
                }
            }
        }

        cells[y][x].setBackgroundColor(Color.WHITE);


        isOpenAllCells[y][x] = true;

        if (countBombs != 0){
            cells[y][x].setTextColor(colorCell[countBombs]);
            cells[y][x].setText(countBombs + "");
        }else{

            for (int i = minI; i <= maxI; i++){
                for (int j = minJ; j <= maxJ; j++){
                    if (i == y && j == x) continue;

                    if (!isOpenAllCells[i][j]){
                        openCell(i, j);
                    }
                }
            }

        }
    }

    public boolean checkOpenAllNoBombsCells(){
        int count = 0;

        for (int i = 0; i < HEIGHT; i++){
            for (int j = 0; j < WIDTH; j++){
                if (isOpenAllCells[i][j]){
                    count++;
                }
            }
        }

        return count == (HEIGHT * WIDTH - amountBombs);
    }

    public void lose(){
        Alert.showAlertDialog(this, "Хотите начать заново?", "ВЫ ПРОИГРАЛИ");
    }

    public void win(){
        Alert.showAlertDialog(this, "Хотите начать заново?", "ВЫ ПОБЕДИЛИ");
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
                cells[i][j].setOnLongClickListener(this);
                cells[i][j].setTag(i + "," + j);
                cellsLayout.addView(cells[i][j]);
            }
    }

}
