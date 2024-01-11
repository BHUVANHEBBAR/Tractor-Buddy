package com.example.tractorbuddy_app;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.List;

public class FarmerViewProfileActivity extends AppCompatActivity {
    private Button gobackbtn;
    private String id;
    private FirebaseFirestore db;
    private TextView txtFname,txtUname, txtLname, txtEmailId, txtPhoneNum, txtGender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile_farmer);
        gobackbtn=(Button) findViewById(R.id.gobackbtn);

        Intent intent = getIntent();
        id = intent.getStringExtra("Id");
        db = FirebaseFirestore.getInstance();

        txtFname = (TextView)findViewById(R.id.txtFname);
        txtLname = (TextView)findViewById(R.id.txtLname);
        txtGender = (TextView)findViewById(R.id.txtGender);
        txtUname = (TextView)findViewById(R.id.txtUname);
        txtEmailId = (TextView)findViewById(R.id.txtEmailid);
        txtPhoneNum = (TextView)findViewById(R.id.txtPhoneNum);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("NewFarmer");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String str = "";
                Boolean flag=false;
                for (DataSnapshot SubSnapshot : snapshot.getChildren()) {
                    NewFarmerClass newClass = SubSnapshot.getValue(NewFarmerClass.class);
                    String ShowDataString = "";
                    if(newClass.getFarmerId().equals(id))
                    {
                        txtFname.setText("First Name : " + newClass.getFirstName());
                        txtLname.setText("Last Name : " + newClass.getLastName());
                        txtGender.setText("Gender : " + newClass.getGender());
                        txtUname.setText("User Name : " + newClass.getUserName());
                        txtEmailId.setText("Email Id : " + newClass.getEmailId());
                        txtPhoneNum.setText("Contact Number : " + newClass.getPhoneNum());
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
        db.collection("NewFarmer").get()
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
                                NewFarmerClass newClass = d.toObject(NewFarmerClass.class);
                                if(d.getId().equals(id))
                                {
                                    txtFname.setText("First Name : " + newClass.getFirstName());
                                    txtLname.setText("Last Name : " + newClass.getLastName());
                                    txtGender.setText("Gender : " + newClass.getGender());
                                    txtUname.setText("User Name : " + newClass.getUserName());
                                    txtEmailId.setText("EmailId : " + newClass.getEmailId());
                                    txtPhoneNum.setText("Phone Num : " + newClass.getPhoneNum());
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

        gobackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FarmerMainActivity.class);
                intent.putExtra("Id",id);
                startActivity(intent);
            }
        });
    }
}