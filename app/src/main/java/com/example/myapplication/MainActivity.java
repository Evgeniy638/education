package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends FragmentActivity {
//    String[] operations = {
//            "0",
//            "1",
//            "2",
//            "3",
//            "4",
//            "5",
//            "6",
//            "7",
//            "8",
//            "9",
//            "C",
//            "+",
//            "-",
//            "*",
//            "/",
//            ".",
//            "=",
//            "BACKSPACE"
//    };

    private static boolean hasPoint = false;
    private static boolean hasOperation = false;
    private static boolean hasResult = false;

    @SuppressLint("StaticFieldLeak")
    private static TextView textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

        textView2.setTextColor(Color.BLACK);
    }

    public static void write(String operation){
        if(operation.equals("*") || operation.equals("+") ||
                operation.equals("-") || operation.equals("/")){

            if(hasResult){
                textView1.setText(textView2.getText().toString());
                textView2.setText("");
                textView2.setTextColor(Color.BLACK);

                hasResult = false;
                hasOperation = false;
                hasPoint = false;
            }

            if (hasOperation){
                String newText = textView1.getText().toString();
                newText = newText.substring(0, newText.length() - 1) + operation;
                textView1.setText(newText);
                return;
            }

            if(hasPoint){
                String text = textView2.getText().toString();

                if(text.charAt(text.length() - 1) == '.') {
                    textView2.setText(text.substring(0, text.length() - 1));
                }

                hasPoint = false;
            }

            if(textView1.getText().length() + textView2.getText().length() == 0
                    && !operation.equals("-"))
                return;

            hasOperation = true;

            String newText = "" + textView1.getText() + textView2.getText() + operation;
            textView1.setText(newText);

            textView2.setText("");

        }else if(operation.equals("=")){
            if(hasOperation){
                String text = textView1.getText().toString();
                text = text.substring(0, text.length() - 1);
                textView1.setText(text);
            }

            if(hasPoint){
                String text = textView2.getText().toString();

                if(text.charAt(text.length() - 1) == '.') {
                    textView2.setText(text.substring(0, text.length() - 1));
                }

                hasPoint = false;
            }

            if(hasResult || textView1.getText().length() + textView2.getText().length() == 0)
                return;

            hasResult = true;
            hasPoint = false;

            String newText = "" + textView1.getText() + textView2.getText() + operation;
            textView1.setText(newText);

            String result = calculate() + "";
            textView2.setText(result);
            textView2.setTextColor(Color.GREEN);
        }else {

            if(hasResult){
                textView1.setText("");
                textView2.setText("");
                textView2.setTextColor(Color.BLACK);

                hasResult = false;
                hasOperation = false;
                hasPoint = false;
            }

            if(textView1.getText().length() + textView2.getText().length() + 1 > 30 ||
                    textView2.getText().length() + 1 > 9){
                return;
            }

            if(operation.equals(".")){
                if(hasPoint) return;

                hasPoint = true;
            }

            hasOperation = false;

            String newText = "";

            if (operation.equals(".") && textView2.getText().length() == 0){
                newText += "0.";
            }else {
                newText = textView2.getText() + operation;
            }

            textView2.setText(newText);
        }
    }

    public static void backspace(){
        if(hasResult){
            textView1.setText("");
            textView2.setTextColor(Color.BLACK);

            hasResult = false;
            hasOperation = false;
        }

        String text = textView2.getText().toString();

        if (!text.equals("") && text.charAt(text.length() - 1) == '.'){
            hasPoint = false;
        }

        String newText = text.equals("") ?"" :text.substring(0, text.length() - 1);

        textView2.setText(newText);
    }

    public static void clear(){
        hasResult = false;
        hasPoint = false;
        hasOperation = false;

        textView1.setText("");
        textView2.setText("");
        textView2.setTextColor(Color.BLACK);
    }

    private static double calculate(){
        double result = 0;

        return result;
    }
}
