package com.example.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private String password = "ASDFGH";
    private String login = "somebody";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void logIn(View view) {
        TextView viewResult = findViewById(R.id.result);
        EditText viewPass = findViewById(R.id.password);
        EditText viewLog = findViewById(R.id.login);

        if(checkLoginAndPassword(viewLog.getText().toString(), viewPass.getText().toString())){
            viewLog.setText("");

            viewPass.setText("");
            viewPass.setHint("На правой полке слева");

            viewResult.setText("Вы успешно авторизовались");
            viewResult.setTextColor(Color.GREEN);
        }else {
            viewLog.setText("");
            viewLog.setHint("кто-то");

            viewPass.setText("");
            viewPass.setHint("На правой полке слева");

            viewResult.setText("Логин и/или пароль введены неправильно");
            viewResult.setTextColor(Color.RED);
        }
    }

    private boolean checkLoginAndPassword(String login, String password){
        return this.login.equals(login) && this.password.equals(password);
    }

}
