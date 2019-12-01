package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AuthenticationActivity extends Activity {
    public static StudySchedule studySchedule = new StudySchedule();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        studySchedule.setLogin(data.getStringExtra("newLogin"));
        studySchedule.setPassword(data.getStringExtra("newPassword"));

        ((EditText)findViewById(R.id.login)).setText(studySchedule.getLogin());
    }

    public void logIn(View view) {
        TextView viewResult = findViewById(R.id.result);
        EditText viewPass = findViewById(R.id.password);
        EditText viewLog = findViewById(R.id.login);

        if(checkLoginAndPassword(viewLog.getText().toString(), viewPass.getText().toString())){
            viewLog.setText("");

            viewPass.setText("");

            viewResult.setText("Вы успешно авторизовались");
            viewResult.setTextColor(Color.GREEN);

            Intent i = new Intent(AuthenticationActivity.this, ScheduleActivity.class);
            startActivity(i);
        }else {
            viewLog.setText("");

            viewPass.setText("");

            viewResult.setText("Логин и/или пароль введены неправильно");
            viewResult.setTextColor(Color.RED);
        }
    }

    private boolean checkLoginAndPassword(String login, String password){
        return studySchedule.getLogin().equals(login) && studySchedule.getPassword().equals(password);
    }

}
