package com.example.tractorbuddy_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class Admin_Approve_RejectTractorActivity extends AppCompatActivity {

    private TextView txtName, txtvnum, txtmileage, txtchasisnum, txtType;
    private TextView txttotalprice, txtprice;
    private TextView txtBankName, txtAccnum, txtIFSCcode, txtBranchName, txtnumofdays;
    private String bankname, address, pincode, ifsccode, branchname, bankId,loanId,userId;
    private Button approvebtn, rejectbtn, gobackbtn;
    private FirebaseFirestore db;
    private String appliedId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_accept_reject_tractorrent);
        db = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        userId=intent.getStringExtra("Id");
        appliedId=intent.getStringExtra("appliedId");

        txtName = (TextView)findViewById(R.id.textTname);
        txtvnum = (TextView)findViewById(R.id.textVnum);
        txtType = (TextView)findViewById(R.id.textType);
        txtmileage = (TextView)findViewById(R.id.textMileage);
        txtchasisnum = (TextView) findViewById(R.id.textChassisnum);
        txtBankName = (TextView)findViewById(R.id.textBankName);
        txtIFSCcode = (TextView)findViewById(R.id.textIFSCCode);
        txtBranchName= (TextView)findViewById(R.id.textBranchName);
        txtnumofdays= (TextView)findViewById(R.id.textNumOfDays);
        txtAccnum= (TextView)findViewById(R.id.textAccNum);
        txttotalprice= (TextView)findViewById(R.id.textTotalPrice);
        txtprice= (TextView)findViewById(R.id.textPrice);

        gobackbtn= (Button)findViewById(R.id.gobackbutton);

        approvebtn = (Button) findViewById(R.id.adminacceptbutton);
        rejectbtn = (Button) findViewById(R.id.adminrejectbutton);
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
        DatabaseReference myRef = database.getReference("NewTractorRent");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String str = "";
                for (DataSnapshot SubSnapshot : snapshot.getChildren()) {
                    NewTractorRentClass newClass = SubSnapshot.getValue(NewTractorRentClass.class);
                    if(newClass.getAppliedId().equals(appliedId))
                    {
                        txtName.setText("Tractor Name : " + newClass.getTractorName());
                        txtchasisnum.setText("Chassis Num : " + newClass.getChasisNum());
                        txtvnum.setText("Vehicle Num : " + newClass.getVehicleNum());
                        txtmileage.setText("Mileage : " + newClass.getMileage());
                        txtType.setText("Tractor Type : " + newClass.getTractorType());
                        txtAccnum.setText("Account Num : "+newClass.getAccnum());
                        txtBankName.setText("Bank Name : "+newClass.getBankname());
                        txtBranchName.setText("Branch Name : "+newClass.getBranchname());
                        txtIFSCcode.setText("IFSC Code : "+newClass.getIfsccode());
                        txtprice.setText("Price/Day : "+newClass.getRentprice());
                        txtnumofdays.setText("NumOfDays : "+newClass.getNumofdays());
                        txttotalprice.setText("Total Price : "+newClass.getTotalprice());
                        break;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Data Access Failed" + error.getMessage());
            }
        });

        /*db.collection("NewTractorRent").get()
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
                DatabaseReference myRef = database.getReference("NewTractorRent");
                myRef.child(appliedId).child("status").setValue("Approved");
                Toast.makeText(getApplicationContext(), "Tractor For Rent Approved Success",
                        Toast.LENGTH_LONG).show();
            }
        });

        rejectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("NewTractorRent");
                myRef.child(appliedId).child("status").setValue("Rejected");
                Toast.makeText(getApplicationContext(), "Tractor For Rent Rejected Success",
                        Toast.LENGTH_LONG).show();
            }
        });

    }
}