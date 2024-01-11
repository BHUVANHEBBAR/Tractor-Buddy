package com.example.tractorbuddy_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FarmerMainActivity extends AppCompatActivity {
private Button viewprofilebtn, applyloanbtn, applytractorforrentbtn, viewapprovedtractorbtn,
        viewapprovedloanbtn, gobackbtn;
private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_main);
        viewprofilebtn = (Button) findViewById(R.id.viewProfileBtn);
        applyloanbtn = (Button) findViewById(R.id.applyLoanBtn);
        applytractorforrentbtn = (Button) findViewById(R.id.applyTractorRentBtn);
        viewapprovedtractorbtn= (Button) findViewById(R.id.viewTractorRentApprovedBtn);
        viewapprovedloanbtn= (Button) findViewById(R.id.viewApprovedLoanBtn);
        gobackbtn= (Button) findViewById(R.id.gobackbtn);

        Intent intent = getIntent();
        id = intent.getStringExtra("Id");

        viewprofilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FarmerViewProfileActivity.class);
                intent.putExtra("Id",id);
                startActivity(intent);
            }
        });
        viewapprovedloanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FarmerViewApprovedLoanActivity.class);
                intent.putExtra("Id",id);
                startActivity(intent);
            }
        });
        applyloanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FarmerApplyLoanActivity.class);
                intent.putExtra("Id",id);
                startActivity(intent);
            }
        });
        applytractorforrentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FarmerApplyTractorRentActivity.class);
                intent.putExtra("Id",id);
                startActivity(intent);
            }
        });
        viewapprovedtractorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FarmerViewApprovedTractorActivity.class);
                intent.putExtra("Id",id);
                startActivity(intent);
            }
        });
        gobackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}