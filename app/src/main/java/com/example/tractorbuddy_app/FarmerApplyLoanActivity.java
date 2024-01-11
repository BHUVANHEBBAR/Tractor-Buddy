package com.example.tractorbuddy_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
import android.os.Bundle;

public class FarmerApplyLoanActivity extends AppCompatActivity {
    private Button viewBtn;
    private ListView list_view;
    private String bankid,userid;
    private String[] array,bankids;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_apply_loan);
        list_view = (ListView) findViewById(R.id.list_view);
        viewBtn = (Button) findViewById(R.id.show_button);
        Intent intent = getIntent();
        userid = intent.getStringExtra("Id");
        Log.d("User Id : ",userid);

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String bank_id = bankids[position];
                Intent intent = new Intent(getApplicationContext(), FarmerApplyLoanActivity1.class);
                Log.d("User Id : ",userid);
                intent.putExtra("Id",userid);
                intent.putExtra("bankid",bank_id);
                startActivity(intent);
            }
        });

        db = FirebaseFirestore.getInstance();
        bankid="";
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("NewBank");

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String str = "";
                        for (DataSnapshot SubSnapshot : snapshot.getChildren()) {
                            NewBankClass newClass = SubSnapshot.getValue(NewBankClass.class);
                            String ShowDataString = "";
                            ShowDataString = "Bank Name : " + newClass.getBankName()+
                                    "\nBranch Name  : " + newClass.getBranchName()+
                                    "\nIFSC Code    : " + newClass.getIfsccode() +
                                    "\nAddress      : " + newClass.getAddress()+
                                    "\nPincode      : " + newClass.getPincode();

                            if (str.length() == 0) {
                                str = str + ShowDataString;
                                bankid=newClass.getBankId();
                            }
                            else {
                                str = str + "," + ShowDataString;
                                bankid+=","+newClass.getBankId();
                            }
                        }
                        Log.d("Data : ", str);
                        bankids = bankid.split(",");
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
                /*db.collection("NewBank").get()
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
                                        NewBankClass newClass = d.toObject(NewBankClass.class);
                                        String ShowDataString = "";
                                        ShowDataString = "Bank Name : " + newClass.getBankName()+
                                                "\nBranch Name  : " + newClass.getBranchName()+
                                                "\nIFSC Code    : " + newClass.getIfsccode() +
                                                "\nAddress      : " + newClass.getAddress()+
                                                "\nPincode      : " + newClass.getPincode();

                                        if (str.length() == 0) {
                                            str = str + ShowDataString;
                                            bankid=d.getId();
                                        }
                                        else {
                                            str = str + "," + ShowDataString;
                                            bankid+=","+d.getId();
                                        }
                                    }
                                    bankids = bankid.split(",");
                                    array = str.split(",");
                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                                            android.R.layout.simple_list_item_1, android.R.id.text1, array);
                                    list_view.setAdapter(adapter);
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