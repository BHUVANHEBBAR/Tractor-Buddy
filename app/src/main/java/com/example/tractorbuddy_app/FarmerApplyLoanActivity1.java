package com.example.tractorbuddy_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.UUID;
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

public class FarmerApplyLoanActivity1 extends AppCompatActivity {

    private TextView txtBankName, txtAddress, txtPincode, txtIFSCcode, txtBranchName;
    private String bankname, address, pincode, ifsccode, branchname, bankId,loanId,userId;
    private Button applybankbtn, gobackbtn;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_apply_loan1);
        db = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        userId=intent.getStringExtra("Id");
        Log.d("User Id : ",userId);
        bankId=intent.getStringExtra("bankid");

        txtAddress = (TextView) findViewById(R.id.textBankAddress);
        txtBankName = (TextView) findViewById(R.id.textBankName);
        txtBranchName = (TextView) findViewById(R.id.textBranchName);
        txtIFSCcode = (TextView) findViewById(R.id.textBankIFSCCode);
        txtPincode = (TextView) findViewById(R.id.textBankPinCode);
        applybankbtn = (Button) findViewById(R.id.applybankloanbutton);
        gobackbtn = (Button) findViewById(R.id.gobackbutton);

        gobackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FarmerMainActivity.class);
                intent.putExtra("Id",userId);
                startActivity(intent);
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("NewBank");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String str = "";
                for (DataSnapshot SubSnapshot : snapshot.getChildren()) {
                    NewBankClass newClass = SubSnapshot.getValue(NewBankClass.class);
                    String ShowDataString = "";
                    if(newClass.getBankId().equals(bankId))
                    {
                        txtBankName.setText("Bank Name : " + newClass.getBankName());
                        txtAddress.setText("Address : " + newClass.getAddress());
                        txtPincode.setText("Pincode : " + newClass.getPincode());
                        txtBranchName.setText("Branch Name : " + newClass.getBranchName());
                        txtIFSCcode.setText("IFSCCode : " + newClass.getIfsccode());
                        bankname=newClass.getBankName();
                        address = newClass.getAddress();
                        pincode = newClass.getPincode();
                        branchname=newClass.getBranchName();
                        ifsccode    =newClass.getIfsccode();
                        break;
                    }
                }
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

        applybankbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*bankname = txtBankName.getText().toString();
                address = txtAddress.getText().toString();
                pincode = txtPincode.getText().toString();
                ifsccode = txtIFSCcode.getText().toString();
                branchname = txtBranchName.getText().toString();*/

                if (TextUtils.isEmpty(bankname)) {
                    txtBankName.setError("Bank Name is Empty");
                    txtBankName.setEnabled(true);
                } else if (TextUtils.isEmpty(branchname)) {
                    txtBranchName.setError("Branch Name is Empty");
                    txtBranchName.setEnabled(true);
                } else if (TextUtils.isEmpty(address)) {
                    txtAddress.setError("Address is Empty");
                    txtAddress.setEnabled(true);
                } else if (TextUtils.isEmpty(ifsccode)) {
                    txtIFSCcode.setError("IFSCCode is Empty");
                    txtIFSCcode.setEnabled(true);
                } else if (TextUtils.isEmpty(pincode)) {
                    txtPincode.setError("Pincode is Empty");
                    txtPincode.setEnabled(true);
                } else {

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference dbRef = database.getReference();
                    dbRef = database.getReference("NewBankLoan");
                    loanId = dbRef.push().getKey();
                    NewBankLoanClass newClass = new NewBankLoanClass(loanId, bankId, userId, bankname, branchname, pincode, address, ifsccode, "Applied");
                    dbRef.child(loanId).setValue(newClass);
                    Toast.makeText(getApplicationContext(), "Loan Applied Successfully",
                            Toast.LENGTH_LONG).show();

                    Log.d("Click on New Bank", "New Bank");
                    /*FirebaseFirestore db = FirebaseFirestore.getInstance();
                    UUID uuid = UUID.randomUUID();
                    loanId = uuid.toString();

                    NewBankLoanClass newClass = new NewBankLoanClass(loanId, bankId, bankname, branchname, pincode, address, ifsccode,userId, "Applied");

                    // Add a new document with a generated ID
                    db.collection("NewBankLoan")
                            .add(newClass)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d("Success : ", "DocumentSnapshot added with ID: " + documentReference.getId());
                                    Toast.makeText(getApplicationContext(), "Bank Loan has been added to Firebase Firestore", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("Error : ", "Error adding document", e);
                                    Toast.makeText(getApplicationContext(), "Bank Loan Failed to apply\n" + e, Toast.LENGTH_LONG).show();
                                }
                            });*/
                }
            }
        });

    }
}