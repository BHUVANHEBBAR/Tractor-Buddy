package com.example.tractorbuddy_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FarmerViewApprovedTractorActivity extends AppCompatActivity {
    private Button viewBtn;
    private ListView list_view;
    private ArrayList<String> staffarray;
    private String[] array;
    private String id;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_approvedtractorrent);
        list_view = (ListView) findViewById(R.id.list_view);
        viewBtn = (Button) findViewById(R.id.show_button);
        db = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        id = intent.getStringExtra("Id");
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("NewTractorRent");

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String str = "";
                        for (DataSnapshot SubSnapshot : snapshot.getChildren()) {
                            NewTractorRentClass newClass = SubSnapshot.getValue(NewTractorRentClass.class);
                            String ShowDataString = "";
                            if(newClass.getUserId().equals(id) && newClass.getStatus().equals("Approved")){
                            ShowDataString = "Tractor Name : " + newClass.getTractorName() +
                                    "\nTractor Type : " + newClass.getTractorType()+
                                    "\nVehicle Num : " + newClass.getVehicleNum() +
                                    "\nVehicle Mileage : " + newClass.getMileage() +
                                    "\nChasis Num : " + newClass.getChasisNum()+
                                    "\nPrice : " + newClass.getRentprice() +
                                    "\nAccount Number : " + newClass.getAccnum()+
                                    "\nBank Name : " + newClass.getBankname()+
                                    "\nBranch Name : " + newClass.getBranchname()+
                                    "\nIFSC Code : " + newClass.getIfsccode()+
                                    "\nNumofDays : " + newClass.getNumofdays()+
                                    "\nTotal Price : " + newClass.getTotalprice();
                            if (str.length() == 0) {
                                str = str + ShowDataString;
                            } else {
                                str = str + "," + ShowDataString;
                            }
                        }
                        }
                        Log.d("Data : ", str);

                        array = str.split(",");
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                                android.R.layout.simple_list_item_1, android.R.id.text1, array);
                        list_view.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        System.out.println("Data Access Failed" + error.getMessage());
                    }
                });
            }
        });
    }
}