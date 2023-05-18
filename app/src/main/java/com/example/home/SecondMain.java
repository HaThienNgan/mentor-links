package com.example.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondMain extends Login {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button btn_logout = null;
        Button btn_close = null;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_main);

        btn_logout = findViewById(R.id.btn_logout);
        btn_close = findViewById(R.id.btn_close);
    }

    public  void logout(View view){
        SharedPreferences sharedpreferences = getSharedPreferences(Login.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
    }

    public void close(View view){
        finish();
    }
}