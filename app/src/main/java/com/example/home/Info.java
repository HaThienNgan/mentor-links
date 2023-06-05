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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationView;

public class Info extends AppCompatActivity {
    ShapeableImageView btn_info_right;
    ImageButton btn_nav;
    Dialog mDialog;
    String[] items_major = {"Ngoại ngữ", "Kinh tế", "Khoa học tự nhiên", "Kỹ thuật", "Thiết kế", "Nghệ thuật", "Khoa học Sức khỏe", "Luật", "KiDao", "Yoga", "Thiền", "Pilates", "Running", "Cycling", "Swimming", "Gym training", "Khởi nghiệp", "SNE", "Đầu tư", "Lãnh đạo", "Quản trị", "Giảm cân", "Quan hệ công chúng", "Tình cảm", "Gia đình", "Trị liệu tâm lí" };
    String[] items_year = {"Năm thứ 1", "Năm thứ 2", "Năm thứ 3", "Năm thứ 4"};
    AutoCompleteTextView list_majors, list_years;
    ArrayAdapter<String> adapterMajor;
    ArrayAdapter<String> adapterYear;

    private CheckBox checkBoxEducation, checkBoxHealth, checkBoxBusiness, checkboxDevelopment;
    private LinearLayout linearLayoutEducation, linearLayoutHealth, linearLayoutBusiness, linearLayoutDevelopment;


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

        //List Majors
        list_majors = findViewById(R.id.list_majors);
        adapterMajor = new ArrayAdapter<>(this, R.layout.list_item,items_major);
        list_majors.setAdapter(adapterMajor);
        list_majors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item: " + item, Toast.LENGTH_SHORT).show();
            }
        });

        //List Year
        list_years = findViewById(R.id.list_years);
        adapterYear = new ArrayAdapter<>(this, R.layout.list_item,items_year);
        list_years.setAdapter(adapterYear);
        list_years.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item: " + item, Toast.LENGTH_SHORT).show();
            }
        });

        // Show CheckBox
        checkBoxEducation = findViewById(R.id.checkBoxEducation);
        checkBoxHealth = findViewById(R.id.checkBoxHealth);
        checkBoxBusiness = findViewById(R.id.checkBoxBusiness);
        checkboxDevelopment = findViewById(R.id.checkBoxDevelopment);
        linearLayoutEducation = findViewById(R.id.linearLayoutEducation);
        linearLayoutHealth = findViewById(R.id.linearLayoutHealth);
        linearLayoutBusiness = findViewById(R.id.linearLayoutBusiness);
        linearLayoutDevelopment = findViewById(R.id.linearLayoutDevelopment);

        boolean isCheckedEdu = checkBoxEducation.isChecked();
        boolean isCheckedHealth = checkBoxHealth.isChecked();
        boolean isCheckedBusiness = checkBoxBusiness.isChecked();
        boolean isCheckedDev = checkboxDevelopment.isChecked();


        updateTextVisibilityEdu(isCheckedEdu);
        updateTextVisibilityHealth(isCheckedHealth);
        updateTextVisibilityBusiness(isCheckedBusiness);
        updateTextVisibilityDev(isCheckedDev);

        checkBoxEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = ((CheckBox)v).isChecked();
                updateTextVisibilityEdu(isChecked);
            }
        });
        checkBoxHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = ((CheckBox)v).isChecked();
                updateTextVisibilityHealth(isChecked);
            }
        });
        checkBoxBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = ((CheckBox)v).isChecked();
                updateTextVisibilityBusiness(isChecked);
            }
        });
        checkboxDevelopment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = ((CheckBox)v).isChecked();
                updateTextVisibilityDev(isChecked);
            }
        });
    }

    private void updateTextVisibilityEdu(boolean isChecked){
        if(isChecked) {
            linearLayoutEducation.setVisibility(View.VISIBLE);
        } else {
            linearLayoutEducation.setVisibility(View.INVISIBLE);
        }
    }
    private void updateTextVisibilityHealth(boolean isChecked){
        if(isChecked) {
            linearLayoutHealth.setVisibility(View.VISIBLE);
        } else {
            linearLayoutHealth.setVisibility(View.INVISIBLE);
        }
    }
    private void updateTextVisibilityBusiness(boolean isChecked){
        if(isChecked) {
            linearLayoutBusiness.setVisibility(View.VISIBLE);
        } else {
            linearLayoutBusiness.setVisibility(View.INVISIBLE);
        }
    }
    private void updateTextVisibilityDev(boolean isChecked){
        if(isChecked) {
            linearLayoutDevelopment.setVisibility(View.VISIBLE);
        } else {
            linearLayoutDevelopment.setVisibility(View.INVISIBLE);
        }
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
//                params.width = (Resources.getSystem().getDisplayMetrics().widthPixels * 50) / 100;
//                params.height = WindowManager.LayoutParams.MATCH_PARENT;
                params.gravity = Gravity.TOP|Gravity.RIGHT|Gravity.BOTTOM;
                window.setAttributes(params);
                mDialog.show();
            }

        });

        btn_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.setContentView(R.layout.info_popup_nav);
                Window window = mDialog.getWindow();
                WindowManager.LayoutParams params = window.getAttributes();
                params.width = WindowManager.LayoutParams.MATCH_PARENT;
                params.width = (Resources.getSystem().getDisplayMetrics().widthPixels * 30) / 100;
                params.height = (Resources.getSystem().getDisplayMetrics().heightPixels * 105) / 100;
                params.gravity = Gravity.LEFT|Gravity.TOP;
                window.setAttributes(params);
                mDialog.show();
            }

        });
    }

}