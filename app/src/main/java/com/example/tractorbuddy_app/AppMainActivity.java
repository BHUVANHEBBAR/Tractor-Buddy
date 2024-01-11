package com.example.tractorbuddy_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AppMainActivity extends AppCompatActivity {
private Button adminLoginbtn, farmerLoginbtn, newFarmerbtn, logoutbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_main);

        adminLoginbtn = (Button)findViewById(R.id.adminLoginBtn);
        farmerLoginbtn = (Button)findViewById(R.id.farmerLoginBtn);
        newFarmerbtn = (Button)findViewById(R.id.newFarmerBtn);
        logoutbtn = (Button)findViewById(R.id.logoutBtn);

        adminLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminLoginActivity.class);
                startActivity(intent);
            }
        });

        farmerLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FarmerLoginActivity.class);
                startActivity(intent);
            }
        });

        newFarmerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewFarmerActivity.class);
                startActivity(intent);
            }
        });

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}