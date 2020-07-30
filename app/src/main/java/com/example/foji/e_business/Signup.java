package com.example.foji.e_business;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.nsd.NsdManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.flags.Flag;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    Button button;
    EditText userName, cityy, cellNo;
    TextView tv1;
    RadioGroup radioGroup;
    RadioButton radioIn, radioEn, radioButton;

    private ProgressDialog progressBar;
    private FirebaseAuth mAuth;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;

    public static final String KEY = "com.example.foji.e_business.KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        userName = (EditText) findViewById(R.id.textnameID);
        cityy = (EditText) findViewById(R.id.textcityID);
        cellNo = (EditText) findViewById(R.id.textcellnoID);
        button = (Button) findViewById(R.id.signupbuttonID);
        tv1 = (TextView) findViewById(R.id.tv1);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        radioIn = (RadioButton) findViewById(R.id.radio_in);
        radioEn = (RadioButton) findViewById(R.id.radio_en);

       // cityy.setFilters(new InputFilter[] {new InputFilter.AllCaps()});

        progressBar = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference();
        //mRef = mDatabase.getReference();

        /* finish();
            if (mAuth.getCurrentUser() != null) {
                startActivity(new Intent(getApplicationContext(), Navigation.class));
        }*/
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (radioEn==null && radioIn==null)
//                    Toast.makeText(Signup.this, "Select Account Type First", Toast.LENGTH_SHORT).show();
//                 else if (radioIn.isChecked())
//                Toast.makeText(Signup.this, "You Selected Investor", Toast.LENGTH_SHORT).show();
//                else if (radioEn.isChecked())
//                Toast.makeText(Signup.this, "You Selected Entrepreneur", Toast.LENGTH_SHORT).show();
//                finish();
//                startActivity(new Intent(Signup.this, Register.class));
                /////////////////////////////////  working code              ////////////////////

                String name = userName.getText().toString();
                String city = cityy.getText().toString();
                String num = cellNo.getText().toString();
                // int num = Integer.parseInt(cellNo.getText().toString());
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(city) || TextUtils.isEmpty(num)) {
                    Toast.makeText(Signup.this, "Enter Data in fields", Toast.LENGTH_SHORT).show();
                } else {
                    int radioID = radioGroup.getCheckedRadioButtonId();
                    radioButton = findViewById(radioID);
                    if (radioButton == null)
                        Toast.makeText(Signup.this, "Select Account Type First", Toast.LENGTH_SHORT).show();
                    else if (radioButton == radioEn) {
                        String key = mRef.child("Entrepreneur").push().getKey();
                        mRef.child("Entrepreneur").child(key).child("name").setValue(name);
                        mRef.child("Entrepreneur").child(key).child("city").setValue(city);
                        mRef.child("Entrepreneur").child(key).child("cell").setValue(num);

                        //// send key for update data///
//                    Intent intent = new Intent(Signup.this, Updateaccount.class);
//                    intent.putExtra(KEY, key);
//                    startActivity(intent);
                        //Updateaccount updateaccount = new Updateaccount(key);
                        //Toast.makeText(Signup.this, "You Selected Entrepreneur", Toast.LENGTH_SHORT).show();
//                    mRef.child("Entrepreneur").child("cell").setValue(num);
//                    mRef.child("Entrepreneur").child("user").setValue(name);
//                            .addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            //Toast.makeText(Signup.this, "Registered as an Entrepreneur", Toast.LENGTH_SHORT).show();
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                                    //Toast.makeText(Signup.this, "Not inserted in Entrepreneur", Toast.LENGTH_SHORT).show();
//                        }
//                    });
                    } else if (radioButton == radioIn) {
                        String key = mRef.child("Investor").push().getKey();
                        mRef.child("Investor").child(key).child("name").setValue(name);
                        mRef.child("Investor").child(key).child("city").setValue(city);
                        mRef.child("Investor").child(key).child("cell").setValue(num);

//                    Intent intent = new Intent(Signup.this, Updateaccount.class);
//                    intent.putExtra(KEY, key);
//                    startActivity(intent);
//                    finish();

//                    mRef.child("Investor").push().child("user").setValue(data);
//                    mRef.child("Investor").push().child("cell").setValue(num);
//                            .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void aVoid) {
//                                    Toast.makeText(Signup.this, "You inserted in Investor", Toast.LENGTH_SHORT).show();
//                                }
//                            }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                                    Toast.makeText(Signup.this, " not inserted in Investor", Toast.LENGTH_SHORT).show();
//                                }
//                    });;

                    }
                    startActivity(new Intent(Signup.this, Register.class));
                }
            }
        });
    }
}