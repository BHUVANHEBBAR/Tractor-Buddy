package com.example.tractorbuddy_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AdminViewTractorRentAppliedActivity extends AppCompatActivity {
    private Button viewBtn;
    private ListView list_view;
    private ArrayList<String> staffarray;
    private String[] array, applied_ids;
    private String appliedid;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_applied_tractor_rent);
        list_view = (ListView) findViewById(R.id.list_view);
        viewBtn = (Button) findViewById(R.id.show_button);
        db = FirebaseFirestore.getInstance();

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String applied_id = applied_ids[position];
                Intent intent = new Intent(getApplicationContext(), Admin_Approve_RejectTractorActivity.class);
                intent.putExtra("Id",id);
                intent.putExtra("appliedId",applied_id);
                startActivity(intent);
            }
        });

        appliedid ="";
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
                            ShowDataString = "Tractor Name : " + newClass.getTractorName()+
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
                                appliedid =newClass.getAppliedId();
                            }
                            else {
                                str = str + "," + ShowDataString;
                                appliedid +=","+newClass.getAppliedId();;
                            }
                        }
                        Log.d("Data : ", str);
                        applied_ids = appliedid.split(",");
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
                /*db.collection("NewBankLoan").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                // after getting the data we are calling on success method
                                // and inside this method we are checking if the received
                                // query snapshot is empty or not.
                                if (!queryDocumentSnapshots.isEmpty()) {
                                    // if the snapshot is not empty we are
                                    // hiding our progress bar and adding
                                    // our data in a list.
                                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                    String str = "";
                                    for (DocumentSnapshot d : list) {
                                        // after getting this list we are passing
                                        // that list to our object class.
                                        NewBankLoanClass newClass = d.toObject(NewBankLoanClass.class);
                                        String ShowDataString = "";
                                        if(newClass.getStatus().equals("Applied")) {
                                            ShowDataString = "Bank Name : " + newClass.getBankName() +
                                                    "\nBank Address  : " + newClass.getAddress() +
                                                    "\nIFSCCode : " + newClass.getIfsccode() +
                                                    "\nPincode    : " + newClass.getPincode() +
                                                    "\nStatus    : " + newClass.getStatus();
                                            if (str.length() == 0) {
                                                str = str + ShowDataString;
                                                loanid=d.getId();
                                            }
                                            else {
                                                str = str + "," + ShowDataString;
                                                loanid+=","+d.getId();
                                            }
                                        }
                                    }
                                    array = str.split(",");
                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                                            android.R.layout.simple_list_item_1, android.R.id.text1, array);
                                    list_view.setAdapter(adapter);
                                    loan_ids=loanid.split(",");
                                } else {
                                    // if the snapshot is empty we are displaying a toast message.
                                    Toast.makeText(getApplicationContext(), "No data found in Database", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // if we do not get any data or any error we are displaying
                                // a toast message that we do not get any data
                                Toast.makeText(getApplicationContext(), "Fail to get the data.", Toast.LENGTH_SHORT).show();
                            }
                        });*/
            }
        });
    }
}