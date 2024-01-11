package com.example.tractorbuddy_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

public class FarmerApplyTractorRentActivity1 extends AppCompatActivity {
    private String tractorId,appliedId,userId;
    private String tname, ttype, vnum, mileage, chasisnum, timage, accnum, bankname,
            branchname, ifsccode, rentprice, totalprice, numofdays;
    private int rent;
    private TextView txtName, txtvnum, txtmileage, txtchasisnum,txttractortype, txttractorrent, txttotalprice;
    private EditText txtBankName, txtAccnum, txtIFSCcode, txtBranchName, txtnumofdays;
    private Button applybankbtn, gobackbtn, calcbtn;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_apply_tractorrent1);
        db = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        userId=intent.getStringExtra("Id");
        tractorId=intent.getStringExtra("tractorid");
        Log.d("User Id : ",userId);
        txtBankName = (EditText)findViewById(R.id.editBankName);
        txtIFSCcode = (EditText)findViewById(R.id.editIFSCCode);
        txtBranchName= (EditText)findViewById(R.id.editBranchName);
        txtnumofdays= (EditText)findViewById(R.id.editNumOfDays);
        txtAccnum= (EditText)findViewById(R.id.editAccNum);
        txtName = (TextView)findViewById(R.id.textTname);
        txtvnum = (TextView)findViewById(R.id.textVnum);
        txtmileage = (TextView)findViewById(R.id.textMileage);
        txtchasisnum = (TextView)findViewById(R.id.textChassisnum);
        txttractorrent = (TextView)findViewById(R.id.textRentPrice);
        txttotalprice = (TextView)findViewById(R.id.textTotalPrice);
        txttractortype = (TextView)findViewById(R.id.textType);

        applybankbtn = (Button) findViewById(R.id.applybankloanbutton);
        gobackbtn = (Button) findViewById(R.id.gobackbutton);
        calcbtn = (Button) findViewById(R.id.calctotalbutton);

        calcbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numofdays = txtnumofdays.getText().toString();
                int num = Integer.parseInt(numofdays);
                totalprice = String.valueOf(num*rent);
                txttotalprice.setText(totalprice);
            }
        });

        gobackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FarmerMainActivity.class);
                intent.putExtra("Id",userId);
                startActivity(intent);
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("NewTractor");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String str = "";
                for (DataSnapshot SubSnapshot : snapshot.getChildren()) {
                    NewTractorClass newClass = SubSnapshot.getValue(NewTractorClass.class);
                    if(newClass.getTractorId().equals(tractorId))
                    {
                        txtName.setText("Tractor Name : " + newClass.getTractorName());
                        txtchasisnum.setText("Chassis Num : " + newClass.getChasisNum());
                        txtvnum.setText("Vehicle Num : " + newClass.getVehicleNum());
                        txtmileage.setText("Mileage : " + newClass.getMileage());
                        txttractortype.setText("Tractor Type : " + newClass.getTractorType());
                        txttractorrent.setText("Tractor Rent : "+newClass.getPrice());
                        rent = Integer.parseInt(newClass.getPrice());

                        tname=newClass.getTractorName();
                        ttype = newClass.getTractorType();
                        vnum= newClass.getVehicleNum();
                        mileage=newClass.getMileage();
                        chasisnum=newClass.getChasisNum();
                        rentprice =newClass.getPrice();
                        rent = Integer.parseInt(rentprice);
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

                bankname = txtBankName.getText().toString();
                accnum = txtAccnum.getText().toString();
                branchname = txtBranchName.getText().toString();
                ifsccode = txtIFSCcode.getText().toString();
                numofdays = txtnumofdays.getText().toString();
                totalprice = txttotalprice.getText().toString();

                if (TextUtils.isEmpty(tname)) {
                    txtName.setError("Tractor Name is Empty");
                    txtName.setEnabled(true);
                } else if (TextUtils.isEmpty(ttype)) {
                    txttractortype.setError("Tractor Type is Empty");
                    txttractortype.setEnabled(true);
                } else if (TextUtils.isEmpty(mileage)) {
                    txtmileage.setError("Mileage is Empty");
                    txtmileage.setEnabled(true);
                } else if (TextUtils.isEmpty(vnum)) {
                    txtvnum.setError("Vehicle Num is Empty");
                    txtvnum.setEnabled(true);
                } else if (TextUtils.isEmpty(chasisnum)) {
                    txtchasisnum.setError("Chassis Num is Empty");
                    txtchasisnum.setEnabled(true);
                }
                else if (TextUtils.isEmpty(accnum)) {
                    txtAccnum.setError("Account Num is Empty");
                    txtAccnum.setEnabled(true);
                }
                else if (TextUtils.isEmpty(bankname)) {
                    txtBankName.setError("Bank Name is Empty");
                    txtBankName.setEnabled(true);
                }
                else if (TextUtils.isEmpty(branchname)) {
                    txtBranchName.setError("Branch Name is Empty");
                    txtBranchName.setEnabled(true);
                }
                else if (TextUtils.isEmpty(ifsccode)) {
                    txtIFSCcode.setError("IFSC Code is Empty");
                    txtIFSCcode.setEnabled(true);
                }
                else if (TextUtils.isEmpty(numofdays) || numofdays.equals("0")) {
                    txtnumofdays.setError("Num Of Days is Empty");
                    txtnumofdays.setEnabled(true);
                }
                else if (TextUtils.isEmpty(totalprice) || totalprice.equals("0")) {
                    txttotalprice.setError("Total Price is Empty");
                    txttotalprice.setEnabled(true);
                }
                else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference dbRef = database.getReference();
                    dbRef = database.getReference("NewTractorRent");
                    appliedId = dbRef.push().getKey();
                    NewTractorRentClass newClass = new NewTractorRentClass(userId, appliedId, tractorId, tname,
                            ttype, vnum, chasisnum, mileage, "Applied", rentprice,
                            accnum, bankname, branchname, ifsccode, numofdays, totalprice);
                    dbRef.child(appliedId).setValue(newClass);
                    Toast.makeText(getApplicationContext(), "Tractor For Rent Inserted Successfully",
                            Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}