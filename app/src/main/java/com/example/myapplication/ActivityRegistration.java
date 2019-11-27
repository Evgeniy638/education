package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

@SuppressLint("Registered")
public class ActivityRegistration extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
    }

    public void changePasswordAndLogin(View v){
        String newLogin = ((EditText) findViewById(R.id.newLogin)).getText().toString();
        String newPassword = ((EditText) findViewById(R.id.newPassword)).getText().toString();
        TextView result = findViewById(R.id.resultRegistration);

        String errorMessage = "";

        if (newLogin.length() <= 0) errorMessage += "Вы не ввели логин \n";
        if (newPassword.length() <= 0) errorMessage += "Вы не ввели пароль";

        if (errorMessage.length() > 0){
            result.setTextColor(Color.RED);
            result.setText(errorMessage);
            return;
        }

        Intent i = new Intent();
        i.putExtra("newLogin", newLogin);
        i.putExtra("newPassword", newPassword);
        setResult(RESULT_OK, i);
        finish();
    }
}
