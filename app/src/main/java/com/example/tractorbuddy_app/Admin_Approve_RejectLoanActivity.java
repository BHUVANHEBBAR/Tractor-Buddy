package com.example.tractorbuddy_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.UUID;

public class Admin_Approve_RejectLoanActivity extends AppCompatActivity {

    private TextView txtBankName, txtAddress, txtPincode, txtIFSCcode, txtBranchName;
    private String bankname, address, pincode, ifsccode, branchname, bankId,loanId,userId;
    private Button approvebtn, rejectbtn, gobackbtn;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_approve_reject_loan);
        db = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        userId=intent.getStringExtra("Id");
        loanId=intent.getStringExtra("loanId");

        txtAddress = (TextView) findViewById(R.id.textBankAddress);
        txtBankName = (TextView) findViewById(R.id.textBankName);
        txtBranchName = (TextView) findViewById(R.id.textBranchName);
        txtIFSCcode = (TextView) findViewById(R.id.textBankIFSCCode);
        txtPincode = (TextView) findViewById(R.id.textBankPinCode);
        approvebtn = (Button) findViewById(R.id.approvebutton);
        rejectbtn = (Button) findViewById(R.id.rejectbutton);
        gobackbtn = (Button) findViewById(R.id.gobackbutton);

        gobackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminMainActivity.class);
                intent.putExtra("Id",userId);
                startActivity(intent);
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("NewBankLoan");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String str = "";
                for (DataSnapshot SubSnapshot : snapshot.getChildren()) {
                    NewBankLoanClass newClass = SubSnapshot.getValue(NewBankLoanClass.class);
                    if(newClass.getLoanId().equals(loanId))
                    {
                        txtBankName.setText("Bank Name : " + newClass.getBankName());
                        txtAddress.setText("Address : " + newClass.getAddress());
                        txtPincode.setText("Pincode : " + newClass.getPincode());
                        txtBranchName.setText("Branch Name : " + newClass.getBranchName());
                        txtIFSCcode.setText("IFSCCode : " + newClass.getIfsccode());
                        break;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Data Access Failed" + error.getMessage());
            }
        });
/*
        db.collection("NewBank").get()
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
                                if(d.getId().equals(bankId))
                                {
                                    txtBankName.setText("Bank Name : " + newClass.getBankName());
                                    txtAddress.setText("Address : " + newClass.getAddress());
                                    txtPincode.setText("Pincode : " + newClass.getPincode());
                                    txtBranchName.setText("Branch Name : " + newClass.getBranchName());
                                    txtIFSCcode.setText("IFSCCode : " + newClass.getIfsccode());
                                    break;
                                }
                            }
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
        approvebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("NewBankLoan");
                myRef.child(loanId).child("status").setValue("Approved");
                Toast.makeText(getApplicationContext(), "Loan Approved Success",
                        Toast.LENGTH_LONG).show();
            }
        });

        rejectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("NewBankLoan");
                myRef.child(loanId).child("status").setValue("Rejected");
                Toast.makeText(getApplicationContext(), "Loan Rejected Success",
                        Toast.LENGTH_LONG).show();
            }
        });

    }
}