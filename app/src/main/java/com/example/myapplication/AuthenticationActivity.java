package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AuthenticationActivity extends Activity {

    private String password = "ASDFGH";
    private String login = "somebody";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.login = data.getStringExtra("newLogin");
        this.password = data.getStringExtra("newPassword");

        ((EditText)findViewById(R.id.login)).setText(login);
    }

    public void registration(View view){
        ((TextView)findViewById(R.id.result)).setText("");

        Intent i = new Intent(AuthenticationActivity.this, ActivityRegistration.class);
        startActivityForResult(i, 1);
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
        }else {
            viewLog.setText("");

            viewPass.setText("");

            viewResult.setText("Логин и/или пароль введены неправильно");
            viewResult.setTextColor(Color.RED);
        }
    }

    private boolean checkLoginAndPassword(String login, String password){
        return this.login.equals(login) && this.password.equals(password);
    }

}
