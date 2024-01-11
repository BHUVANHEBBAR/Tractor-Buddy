package com.example.tractorbuddy_app;

import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.net.Uri;
import java.io.IOException;
import java.io.InputStream;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.UUID;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.FileInputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import android.util.Patterns;

import java.util.Calendar;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.security.SecureRandom;
import java.util.Calendar;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
public class NewTractorActivity extends AppCompatActivity {

    private String imageId;
    private static final int SELECT_PICTURE = 100;
    private static final String TAG = "StoreImageActivity";
    private Uri selectedImageUri;
    private DBHelper dbHelper;
    private String imageType;
    private ImageView tractorPicImage;
    // this function is triggered when
    // the Select Image Button is clicked
    void imageChooser() {
        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                selectedImageUri = data.getData();
                Log.d("Path : ", selectedImageUri.getPath());
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                        tractorPicImage.setImageURI(selectedImageUri);
                }
            }
        }
    }

    boolean saveImageInDB() {
        try {
            dbHelper.open();
            InputStream iStream = getContentResolver().openInputStream(selectedImageUri);
            byte[] inputData = Utils.getBytes(iStream);
            Log.d("ImageType ", imageType);
            dbHelper.insertImage(imageId, imageType, inputData);
            dbHelper.close();
            return true;
        } catch (IOException ioe) {
            Log.e(TAG, "<saveImageInDB> Error : " + ioe.getLocalizedMessage());
            dbHelper.close();
            return false;
        }
    }
    private String tname, ttype, vnum, mileage, chasisnum, timage, price;
    private EditText txtName, txtvnum, txtmileage, txtchasisnum, txttractorprice;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private FirebaseFirestore db;
    private Button newtractorbutton, gobackbtn, uploadtractorbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tractor);

        UUID uuid=UUID.randomUUID(); //Generates random UUID
        imageId = uuid.toString();

        txtName = (EditText)findViewById(R.id.editTextTname);
        txtvnum = (EditText)findViewById(R.id.editTextVnum);
        txtmileage = (EditText)findViewById(R.id.editTextMileage);
        txtchasisnum = (EditText)findViewById(R.id.editCharseNumber);
        txttractorprice = (EditText)findViewById(R.id.editTextPrice);
        radioGroup = (RadioGroup)findViewById(R.id.typeradioGroup);
        newtractorbutton = (Button)findViewById(R.id.newtractorbutton);
        gobackbtn= (Button)findViewById(R.id.gobackbutton);
        tractorPicImage = (ImageView)findViewById(R.id.tractorPicImage);
        uploadtractorbutton= (Button)findViewById(R.id.uploadtractorbutton);

        // Create the Database helper object
        dbHelper = new DBHelper(this);
        uploadtractorbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(saveImageInDB())
                    Toast.makeText(getApplicationContext(), "Tractor Image Saved", Toast.LENGTH_SHORT).show();
            }
        });

        db = FirebaseFirestore.getInstance();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
                ttype = radioButton.getText().toString();
            }
        });

        tractorPicImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageType="Tractor";
                imageChooser();
            }
        });

        gobackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminMainActivity.class);
                startActivity(intent);
            }
        });

        newtractorbutton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                tname=txtName.getText().toString();
                vnum=txtvnum.getText().toString();
                mileage=txtmileage.getText().toString();
                chasisnum=txtchasisnum.getText().toString();
                price = txttractorprice.getText().toString();

                if(TextUtils.isEmpty(tname))
                {
                    Toast.makeText(getApplicationContext(), "Tractor Name is Empty", Toast.LENGTH_SHORT).show();
                    txtName.setFocusable(true);
                }
                else if(TextUtils.isEmpty(vnum))
                {
                    Toast.makeText(getApplicationContext(), "Vehicle Num is Empty", Toast.LENGTH_SHORT).show();
                    txtvnum.setFocusable(true);
                }
                else if(TextUtils.isEmpty(mileage))
                {
                    Toast.makeText(getApplicationContext(), "Mileage is Empty", Toast.LENGTH_SHORT).show();
                    txtmileage.setFocusable(true);
                }else if(TextUtils.isEmpty(chasisnum))
                {
                    txtchasisnum.setError("Chasis Num is Empty");
                    txtchasisnum.setFocusable(true);
                }
                else if(TextUtils.isEmpty(ttype))
                {
                    Toast.makeText(getApplicationContext(), "Tractor Type is Empty", Toast.LENGTH_SHORT).show();
                    radioGroup.setFocusable(true);
                }
                else if(TextUtils.isEmpty(price))
                {
                    Toast.makeText(getApplicationContext(), "Tractor Price/Day is Empty", Toast.LENGTH_SHORT).show();
                    txttractorprice.setFocusable(true);
                }
                else{
                    try {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference dbRef = database.getReference();
                    dbRef = database.getReference("NewTractor");
                    //userId = dbRef.push().getKey();
                    NewTractorClass newClass =new NewTractorClass(imageId, tname, ttype, vnum, chasisnum, mileage, imageId, price);
                    dbRef.child(imageId).setValue(newClass);
                    Toast.makeText(getApplicationContext(), "Tractor has been added to Firebase Firestore", Toast.LENGTH_SHORT).show();
                    Log.d("Tractor Saved Success ", "New Tractor");
                    }catch(Exception e)
                    {
                        Log.d("Exception : ", e.getMessage());
                    }
                }
            }
        });
    }
    //Accept Only Alphabet in EditText
    public static InputFilter acceptonlyAlphabetValuesnotNumbersMethod() {
        return new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                boolean isCheck = true;
                StringBuilder sb = new StringBuilder(end - start);
                for (int i = start; i < end; i++) {
                    char c = source.charAt(i);
                    if (isCharAllowed(c)) {
                        sb.append(c);
                    } else {
                        isCheck = false;
                    }
                }
                if (isCheck)
                    return null;
                else {
                    if (source instanceof Spanned) {
                        SpannableString spannableString = new SpannableString(sb);
                        TextUtils.copySpansFrom((Spanned) source, start, sb.length(), null, spannableString, 0);
                        return spannableString;
                    } else {
                        return sb;
                    }
                }
            }

            private boolean isCharAllowed(char c) {
                Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
                Matcher match = pattern.matcher(String.valueOf(c));
                return match.matches();
            }
        };
    }
}