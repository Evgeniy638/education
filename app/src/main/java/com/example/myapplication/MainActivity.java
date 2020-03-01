package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class MainActivity extends FragmentActivity {

    private static boolean hasPoint = false;
    private static boolean hasOperation = false;
    private static boolean hasResult = false;

    private static int countChar = 0;

    @SuppressLint("StaticFieldLeak")
    private static TextView textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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

            if (hasOperation && countChar != 0){
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

            if(countChar == 0 && (!operation.equals("-") || hasOperation) )
                return;

            hasOperation = true;

            String newText;

            if (textView1.getText().length() == 0){
                newText = textView2.getText() + "\u200B" + operation;
            }else {
                newText = textView1.getText() + "\u200B" +
                        textView2.getText() + "\u200B" + operation;
            }

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

            if(hasResult || countChar == 0)
                return;

            hasResult = true;
            hasPoint = false;
            countChar = textView2.getText().length();

            String newText = textView1.getText() + "\u200B" +
                    textView2.getText() + "\u200B" + operation;

            textView1.setText(newText);

            String result = calculate(textView1.getText().toString()) + "";
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

            if(countChar > 30 ||
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
            countChar++;
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
        if (text.length() != 0) countChar--;
    }

    public static void clear(){
        hasResult = false;
        hasPoint = false;
        hasOperation = false;
        countChar = 0;

        textView1.setText("");
        textView2.setText("");
        textView2.setTextColor(Color.BLACK);
    }

    private static double calculate(String string){
        while (string.charAt(0) == '\u200B'){
            string = string.substring(1);
        }

        if(string.charAt(0) == '-'){
            string = "0\u200B" + string;
        }

        if(string.charAt(string.length() - 1) == '='){
            string = string.substring(0, string.length() - 1);
        }

        String[] stings = string.split("\u200B");

        ArrayList<String> arrayList = getPolishNotation(stings);

        while (arrayList.indexOf("") >= 0){
            arrayList.remove("");
        }

        return getResult(arrayList);
    }

    private static double getResult(ArrayList<String> arr){
        ArrayList<Double> doubles = new ArrayList<>();

        for (int i = 0; i < arr.size(); i++){
            if(isDouble(arr.get(i))){
                doubles.add(Double.parseDouble(arr.get(i)));
            }else if(arr.get(i).equals("+")){
                double tmp = doubles.get(doubles.size() - 2) + doubles.get(doubles.size() - 1);

                doubles.remove(doubles.size() - 1);
                doubles.remove(doubles.size() - 1);

                doubles.add(tmp);
            }else if(arr.get(i).equals("-")){
                double tmp = doubles.get(doubles.size() - 2) - doubles.get(doubles.size() - 1);

                doubles.remove(doubles.size() - 1);
                doubles.remove(doubles.size() - 1);

                doubles.add(tmp);
            }else if(arr.get(i).equals("*")){
                double tmp = doubles.get(doubles.size() - 2) * doubles.get(doubles.size() - 1);

                doubles.remove(doubles.size() - 1);
                doubles.remove(doubles.size() - 1);

                doubles.add(tmp);
            }else {
                double tmp = doubles.get(doubles.size() - 2) / doubles.get(doubles.size() - 1);

                doubles.remove(doubles.size() - 1);
                doubles.remove(doubles.size() - 1);

                doubles.add(tmp);
            }
        }

        return doubles.get(0);
    }

    private static ArrayList<String> getPolishNotation(String[] arr){
        ArrayList<String> newArr = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        for(int i = 0; i < arr.length; i++){
            if(isDouble(arr[i])){
                newArr.add(arr[i]);
            }else if(arr[i].equals(")")){

                while (!stack.peek().equals("(")){
                    newArr.add(stack.pop());
                }

                stack.pop();

            }else if(arr[i].equals("+") || arr[i].equals("-")) {
                while (!stack.empty() && (stack.peek().equals("+") || stack.peek().equals("-") ||
                        stack.peek().equals("*") || stack.peek().equals("/"))){
                    newArr.add(stack.pop());
                }

                stack.push(arr[i]);
            }else if(arr[i].equals("*") || arr[i].equals("/")) {
                while (!stack.empty() && (stack.peek().equals("*") || stack.peek().equals("/"))){
                    newArr.add(stack.pop());
                }

                stack.push(arr[i]);
            }else{
                stack.push(arr[i]);
            }
        }

        while (!stack.empty()){
            newArr.add(stack.pop());
        }

        return newArr;
    }

    private static boolean isDouble(String s) throws NumberFormatException {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
