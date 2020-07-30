package com.example.foji.e_business;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity  {
   // private static final String TAG = "LOGINACTIVITY";
    Button loginbutton;
    EditText emailtext, passwordtext;
    TextView forgetpassword, signup;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    RadioGroup logradiogrp;
    RadioButton loginin, loginen,radioLogButton;
    public static final String EXTRA_MESSAGE =
            "com.example.foji.e_business.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // imgetc = findViewById(R.id.imggggawein);
        emailtext = (EditText) findViewById(R.id.emailtextID);
        passwordtext = (EditText) findViewById(R.id.passwordtextID);
        signup = (TextView) findViewById(R.id.textsignupID);
        //forgetpassword = (TextView) findViewById(R.id.textforgetID);
        loginbutton = (Button) findViewById(R.id.loginbuttonID);
        logradiogrp = (RadioGroup) findViewById(R.id.radiogroup);
        loginin = (RadioButton) findViewById(R.id.login_in);
        loginen = (RadioButton) findViewById(R.id.login_en);

        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
///////////////////          if user is already logged in  /// not log in again
        if (mAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(), Navigation.class));
        }

//        signup.setOnClickListener(this);
//        forgetpassword.setOnClickListener(this);
//        loginbutton.setOnClickListener(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Signup.class));
            }
        });

    }

    public void Signing(View view) {

        Intent intent = new Intent(Login.this, Investors.class);
                               /* String message = emailtext.getText().toString();
                                intent.putExtra(EXTRA_MESSAGE, message);*/
        startActivity(intent);
       /* final String em = emailtext.getText().toString();
        String pass = passwordtext.getText().toString();
        if (TextUtils.isEmpty(em)) {
            Toast.makeText(this, "email missing", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Password missing", Toast.LENGTH_SHORT).show();
            return;
        }
        int radioID = logradiogrp.getCheckedRadioButtonId();
        radioLogButton = findViewById(radioID);
        if (radioLogButton == null)
            Toast.makeText(Login.this, "Select Account Type First", Toast.LENGTH_SHORT).show();
        else if (radioLogButton == loginen) {
            progressDialog.setMessage("Sigining EN");
            progressDialog.show();
            mAuth.signInWithEmailAndPassword(em, pass)
                    .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(Login.this, Navigation.class);
                               *//* String message = emailtext.getText().toString();
                                intent.putExtra(EXTRA_MESSAGE, message);*//*
                                startActivity(intent);
                                progressDialog.dismiss();
                                finish();
                            } else {
                                Toast.makeText(Login.this, "Enter Correct Password and email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

            //progressDialog.dismiss();*/
        }
        /*else if(radioLogButton == loginin) {
            progressDialog.setMessage("Sigining In");
            progressDialog.show();
            {
            mAuth.signInWithEmailAndPassword(em, pass)
                    .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Intent intent = new Intent(Login.this, Investors.class);
                                startActivity(intent);
                                progressDialog.dismiss();
                                finish();
                            }
                            else {
                                Toast.makeText(Login.this, "Enter Correct Password and email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
              //  progressDialog.dismiss();
        }
    }
       // progressDialog.dismiss();
}*/
}