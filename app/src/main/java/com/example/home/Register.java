package com.example.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.home.R;

public class Register extends AppCompatActivity {
    TextView tvLogin;
    Toolbar toolbar;
    //    DrawerLayout drawerLayout;
//    NavigationView navigationView;
    Dialog myDialog;
    ImageButton btnMenuBottom, btnRightMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inIt();
//        actionToolBar();
        myDialog = new Dialog(this);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, MainActivity.class);
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

//    public void actionToolBar(){
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        toolbar.setNavigationIcon(R.drawable.ic_action_menu);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                drawerLayout.openDrawer(GravityCompat.START);
//            }
//        });
//    }

    public void inIt(){
        tvLogin = findViewById(R.id.tvLogin);
        toolbar = findViewById(R.id.toolBar);
//        drawerLayout = findViewById(R.id.drawerLayout);
//        navigationView = findViewById(R.id.navigationView);
        btnMenuBottom = findViewById(R.id.btn_bottom);
        btnRightMenu = findViewById(R.id.btn_menu);
    }
}