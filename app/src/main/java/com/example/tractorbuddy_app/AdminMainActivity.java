package com.example.tractorbuddy_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminMainActivity extends AppCompatActivity {
    private Button newTractorbtn, viewTractorsbtn,
            viewFarmersbtn, newBankbtn, viewBankbtn, logoutbtn,viewAppliedLoanBtn,
            viewApprovedLoanBtn,viewTractorRentAppliedBtn,viewTractorRentApprovedBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        newTractorbtn = (Button)findViewById(R.id.newTractorBtn);
        viewFarmersbtn = (Button)findViewById(R.id.viewFarmersBtn);
        viewTractorsbtn = (Button)findViewById(R.id.viewTractorsBtn);
        newBankbtn = (Button)findViewById(R.id.newBankBtn);
        viewBankbtn = (Button)findViewById(R.id.viewBanksBtn);
        viewAppliedLoanBtn = (Button)findViewById(R.id.viewAppliedLoanBtn);
        logoutbtn = (Button)findViewById(R.id.logoutBtn);
        //viewApprovedLoanBtn = (Button)findViewById(R.id.viewApprovedLoanBtn);
        viewTractorRentAppliedBtn = (Button)findViewById(R.id.viewTractorRentAppliedBtn);
        //viewTractorRentApprovedBtn = (Button)findViewById(R.id.viewTractorRentApprovedBtn);

        viewTractorRentAppliedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminViewTractorRentAppliedActivity.class);
                startActivity(intent);
            }
        });

        newTractorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewTractorActivity.class);
                startActivity(intent);
            }
        });

        viewAppliedLoanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminViewLoanAppliedActivity.class);
                startActivity(intent);
            }
        });

        viewFarmersbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminViewFarmersActivity.class);
                startActivity(intent);
            }
        });

        newBankbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewBankActivity.class);
                startActivity(intent);
            }
        });

        viewBankbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminViewBanksActivity.class);
                startActivity(intent);
            }
        });

        viewTractorsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminViewTractorsActivity.class);
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