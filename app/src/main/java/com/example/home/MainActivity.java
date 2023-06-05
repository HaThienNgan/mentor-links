package com.example.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.home.Adapter.CourseAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

import me.relex.circleindicator.CircleIndicator3;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout mDrawerLayout;
    private RecyclerView courseRecyclerView;
    Dialog mDialog;
    ImageButton btn_bottom;
    ImageButton btn_menu;
    TextView tvLoginRegisterMenu;
    TextView searchFragment, newCourseFragment, highlightFragment;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDialog = new Dialog(this);

        //Recycler View - Slide View
        replaceFragment(new SearchFragment());
        replaceFragment(new newCourseFragment());
        replaceFragment(new HighLightFragment());

        //fragment
//        getSupportFragmentManager().beginTransaction().add(R.id.container, new SearchFragment()).commit();
        searchFragment = findViewById(R.id.search_fragment);
        newCourseFragment = findViewById(R.id.newCourse_fragment);
        highlightFragment = findViewById(R.id.highlight_fragment);
        searchFragment.setOnClickListener(this::onClick);
        newCourseFragment.setOnClickListener(this::onClick);
        highlightFragment.setOnClickListener(this::onClick);


        //Link
//        tvLoginRegisterMenu = findViewById(R.id.tvLoginRegister);
//        tvLoginRegisterMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, Login.class);
//                startActivity(intent);
//            }
//        });
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();
    }

    public void ShowPopup(View v){
        ImageButton btn_bottom = findViewById(R.id.btn_bottom);
        ImageButton btn_menu = findViewById(R.id.btn_menu);
        btn_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.setContentView(R.layout.popup_bottom);
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
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.setContentView(R.layout.popup_menu);
                Window window = mDialog.getWindow();
                WindowManager.LayoutParams params = window.getAttributes();
                params.width = (Resources.getSystem().getDisplayMetrics().widthPixels * 70) / 100;
                params.height = WindowManager.LayoutParams.MATCH_PARENT;
                params.gravity = Gravity.TOP|Gravity.RIGHT|Gravity.BOTTOM;
                window.setAttributes(params);

                tvLoginRegisterMenu = mDialog.findViewById(R.id.tvLoginRegister);
                tvLoginRegisterMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, Login.class);
                        startActivity(intent);
                    }
                });

                mDialog.show();
            }

        });
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.search_fragment){
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new SearchFragment()).commit();
        } else if (v.getId() == R.id.newCourse_fragment) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new newCourseFragment()).commit();
        }else if (v.getId() == R.id.highlight_fragment){
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new HighLightFragment()).commit();
        }
    }

}