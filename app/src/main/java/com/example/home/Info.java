package com.example.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationView;

public class Info extends AppCompatActivity {
    ShapeableImageView btn_info_right;
    ImageButton btn_nav;
    Dialog mDialog;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        mDialog = new Dialog(this);
        ShowPopup();

    }

    public void ShowPopup(){
        btn_info_right = findViewById(R.id.btnMenuTopRight);
        btn_nav = findViewById(R.id.btn_nav);
        btn_info_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.setContentView(R.layout.info_popup_right);
                Window window = mDialog.getWindow();
                WindowManager.LayoutParams params = window.getAttributes();
//                params.width = (Resources.getSystem().getDisplayMetrics().widthPixels * 30) / 30;
//                params.height = WindowManager.LayoutParams.MATCH_PARENT;
                params.gravity = Gravity.TOP|Gravity.RIGHT|Gravity.BOTTOM;
                window.setAttributes(params);
                mDialog.show();
            }

        });

        btn_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.setContentView(R.layout.popup_menu);
                Window window = mDialog.getWindow();
                WindowManager.LayoutParams params = window.getAttributes();
                //params.width = WindowManager.LayoutParams.MATCH_PARENT;
                params.width = (Resources.getSystem().getDisplayMetrics().widthPixels * 98) / 100;
                params.height = (Resources.getSystem().getDisplayMetrics().heightPixels * 70) / 100;
                params.gravity = Gravity.BOTTOM;
                window.setAttributes(params);
                mDialog.show();
            }

        });
    }

}