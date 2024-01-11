package com.example.tractorbuddy_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
//import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

import java.security.SecureRandom;
import java.util.List;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import android.os.Bundle;
import android.text.InputType;

import android.content.Context;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.nio.charset.StandardCharsets;
import java.security.*;

public class FarmerLoginActivity extends AppCompatActivity {
    private Button signInBtn, goBackBtn;
    private EditText txtUname, txtPwd;
    private String username, password, id;
    private FirebaseFirestore db;
    private Boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_login);
        signInBtn = (Button) findViewById(R.id.loginBtn);
        goBackBtn = (Button) findViewById(R.id.gobackBtn);
        txtUname = (EditText) findViewById(R.id.editTextUname);
        txtPwd = (EditText) findViewById(R.id.editTextPassword);

        txtUname.setText("");
        txtPwd.setText("");

        db = FirebaseFirestore.getInstance();
        flag=Boolean.FALSE;
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = txtUname.getText().toString();
                password = txtPwd.getText().toString();

                if (TextUtils.isEmpty(username)) {
                    txtUname.setError("User Name is Empty");
                    txtUname.setFocusable(true);
                } else if (TextUtils.isEmpty(password)) {
                    txtPwd.setError("Password is Empty");
                    txtPwd.setFocusable(true);
                } else {
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
                                if(username.equals(newClass.getUserName()) && password.equals(newClass.getPassword()))
                                {
                                    id=newClass.getFarmerId();
                                    flag=Boolean.TRUE;
                                    break;
                                }
                            }
                            if(flag){
                                Intent intent = new Intent(getApplicationContext(), FarmerMainActivity.class);
                                intent.putExtra("Id", id);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Invalid UserName/Password", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            System.out.println("Data Access Failed" + error.getMessage());
                        }
                    });
                }
            }
        });

        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}