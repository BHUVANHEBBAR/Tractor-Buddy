package com.example.tractorbuddy_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

public class FarmerApplyTractorRentActivity extends AppCompatActivity {
    private Button viewBtn;
    private ListView list_view;
    private String tractorid,userid;
    private String[] array, tractorids;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_apply_tractorrent);
        list_view = (ListView) findViewById(R.id.list_view);
        viewBtn = (Button) findViewById(R.id.show_button);
        Intent intent = getIntent();
        userid = intent.getStringExtra("Id");

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tractor_id = tractorids[position];
                Intent intent = new Intent(getApplicationContext(), FarmerApplyTractorRentActivity1.class);
                intent.putExtra("Id",userid);
                intent.putExtra("tractorid",tractor_id);
                startActivity(intent);
            }
        });

        db = FirebaseFirestore.getInstance();
        tractorid ="";
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("NewTractor");

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String str = "";
                        for (DataSnapshot SubSnapshot : snapshot.getChildren()) {
                            NewTractorClass newClass = SubSnapshot.getValue(NewTractorClass.class);
                            String ShowDataString = "";
                            ShowDataString = "Tractor Name : " + newClass.getTractorName()+
                                    "\nTractor Type : " + newClass.getTractorType()+
                                    "\nVehicle Num : " + newClass.getVehicleNum() +
                                    "\nVehicle Mileage : " + newClass.getMileage() +
                                    "\nChasis Num : " + newClass.getChasisNum()+
                                    "\nTractor Rent : " + newClass.getPrice();
                            if (str.length() == 0) {
                                str = str + ShowDataString;
                                tractorid =newClass.getTractorId();
                            }
                            else {
                                str = str + "," + ShowDataString;
                                tractorid +=","+newClass.getTractorId();
                            }
                        }
                        Log.d("Data : ", str);
                        tractorids = tractorid.split(",");
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
                /*db.collection("NewTractor").get()
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
                                        NewTractorClass newClass = d.toObject(NewTractorClass.class);
                                        String ShowDataString = "";
                                        ShowDataString = "Tractor Name : " + newClass.getTractorName()+
                                                "\nTractor Type : " + newClass.getTractorType()+
                                                "\nVehicle Num : " + newClass.getVehicleNum() +
                                                "\nVehicle Mileage : " + newClass.getMileage() +
                                                "\nChasis Num : " + newClass.getChasisNum();

                                        if (str.length() == 0) {
                                            str = str + ShowDataString;
                                            tractorid =d.getId();
                                        }
                                        else {
                                            str = str + "," + ShowDataString;
                                            tractorid +=","+d.getId();
                                        }
                                    }
                                    tractorids = tractorid.split(",");
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