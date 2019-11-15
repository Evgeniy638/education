package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    // Вызывается при создании Активности
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Инициализирует Активность.
        setContentView(R.layout.activity_main);
    }

    /** Вызывается при нажатии пользователем на кнопку Решить */
        public void solveEquation(View view) {
            double a = Double.parseDouble( ((EditText)
                    findViewById(R.id.coefficient_a)).getText().toString());
            double b = Double.parseDouble( ((EditText)
                    findViewById(R.id.coefficient_b)).getText().toString());
            double c = Double.parseDouble( ((EditText)
                    findViewById(R.id.coefficient_c)).getText().toString());

            double D = Math.pow(b, 2) - 4 * a * c;

            TextView result = (TextView) findViewById(R.id.result);

            if (D < 0) {
                result.setText("Корней нет");
            }else if(D == 0){
                result.setText("" + String.valueOf(- b / (2 * a)));
            }else {
                result.setText("" + String.valueOf((- b + Math.pow(D, 0.5)) / (2 * a)) + "\n"
                        + String.valueOf((- b - Math.pow(D, 0.5)) / (2 * a)));
            }
        }

}
