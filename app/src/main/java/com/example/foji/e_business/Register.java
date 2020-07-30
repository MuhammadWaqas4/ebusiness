package com.example.foji.e_business;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity implements View.OnClickListener {
    Button button;
    EditText ed1, ed2;
    private ProgressDialog progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        button= (Button)findViewById(R.id.button);
        ed1=(EditText)findViewById(R.id.emailed1);
        ed2=(EditText)findViewById(R.id.emailed2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
       /* if (mAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(), Navigation.class));
        }*/

        progressBar = new ProgressDialog(this);
        button.setOnClickListener(this);
        //tv1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view==button)
            register();
       //if (view==tv1)
        //login();
    }

    private void register() {
        String email = ed1.getText().toString();
        String pass = ed2.getText().toString();
        if (validation(email) == true) {
            if (pass != null && pass.length() > 6) {
                progressBar.setMessage("Registering");
                progressBar.show();
                mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(Register.this, "Register Successfully", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(Register.this, Login.class);
                        startActivity(i);
                        finish();
                    }
                }).addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        Toast.makeText(Register.this, "Retry", Toast.LENGTH_SHORT).show();
                    }
                });
                progressBar.dismiss();
            }else
                ed2.setError("Enter more than 6 digit password");
        } else {
            Toast.makeText(getApplicationContext(), "Invalid email", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean validation (String email) {
            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            String emailValidate = ed1.getText().toString().trim();


// onClick of button perform this simplest code.
            if (emailValidate.matches(emailPattern)) {
//            if(pass.isEmpty()|| pass.length()<6)
//            {
//                password.setError("Enter Password");
//                return true;
//            }
                //Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show()
                return true;
            } else {
                // Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

}
