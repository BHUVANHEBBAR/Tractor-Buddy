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
                }
            }
        });

    }
}