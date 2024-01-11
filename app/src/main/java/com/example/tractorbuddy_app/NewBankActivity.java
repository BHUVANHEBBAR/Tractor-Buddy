package com.example.tractorbuddy_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NewBankActivity extends AppCompatActivity {

    private EditText txtBankName, txtAddress, txtPincode, txtIFSCcode, txtBranchName;
    private String bankname, address, pincode, ifsccode, branchname, bankId;
    private Button newbankbtn, gobackbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_bank);

        txtAddress = (EditText) findViewById(R.id.editBankAddress);
        txtBankName = (EditText) findViewById(R.id.editBankName);
        txtBranchName = (EditText) findViewById(R.id.editBranchName);
        txtIFSCcode = (EditText) findViewById(R.id.editIFSCCode);
        txtPincode = (EditText) findViewById(R.id.editPincode);
        newbankbtn = (Button) findViewById(R.id.newbankbutton);
        gobackbtn = (Button) findViewById(R.id.gobackbutton);

        gobackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminMainActivity.class);
                startActivity(intent);
            }
        });

        newbankbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bankname = txtBankName.getText().toString();
                address = txtAddress.getText().toString();
                pincode = txtPincode.getText().toString();
                ifsccode = txtIFSCcode.getText().toString();
                branchname = txtBranchName.getText().toString();

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
                    Log.d("Click on New Bank", "New Bank");

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference dbRef = database.getReference();
                    dbRef = database.getReference("NewBank");
                    bankId = dbRef.push().getKey();
                    NewBankClass newClass = new NewBankClass(bankId, bankname, branchname, pincode, address, ifsccode);
                    dbRef.child(bankId).setValue(newClass);
                    Toast.makeText(getApplicationContext(), "New Bank Inserted Successfully",
                            Toast.LENGTH_LONG).show();


                }
            }
        });

    }
}