package com.example.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Login extends AppCompatActivity {


    TextView tvFogotPassword, tvRegister;
    EditText edtUseName, edtPassword;
    Toolbar toolbar;
    //    DrawerLayout drawerLayout;
//    NavigationView navigationView;
    Dialog myDialog;

    ImageButton btnMenuBottom, btnRightMenu;
    Intent in;

    Button btnLogin;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String UserName = "usernameKey";
    public static final String PassWord = "passwordKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inIt();

        //Login
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email =  edtUseName.getText().toString();
                String password = edtPassword.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(UserName, email);
                editor.putString(PassWord, password);
                editor.commit();

                in = new Intent(Login.this, SecondMain.class);
                startActivity(in);
            }
        });

//        actionToolBar();
        myDialog = new Dialog(this);

        tvFogotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, ForgotPassword.class);
                startActivity(intent);
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        btnMenuBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.setContentView(R.layout.popup_bottom);
                myDialog.show();
            }
        });

        btnRightMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.setContentView(R.layout.popup_menu);
                myDialog.show();
            }
        });
    }
    public void inIt(){
        tvFogotPassword = findViewById(R.id.tvFogotPassword);
        tvRegister = findViewById(R.id.tvRegister);
        toolbar = findViewById(R.id.toolBar);
        edtUseName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
//        drawerLayout = findViewById(R.id.drawerLayout);
//        navigationView = findViewById(R.id.navigationView);
        btnMenuBottom = findViewById(R.id.btnMenuBottom);
        btnRightMenu = findViewById(R.id.btnRightMenu);
        btnLogin = findViewById(R.id.btnLogin);
    }
}