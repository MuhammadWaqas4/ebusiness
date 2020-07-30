package com.example.foji.e_business;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class choose extends AppCompatActivity {
    Button buttonsignup, buttonlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        buttonsignup = findViewById(R.id.choosesignupID);
        buttonsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(choose.this, Signup.class);
                startActivity(intent);
                Toast.makeText(choose.this, "Enter following Detail", Toast.LENGTH_SHORT).show();
            }
        });
        buttonlogin = findViewById(R.id.chooseloginID);
        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(choose.this, "Enter Details", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(choose.this, Login.class);
                startActivity(intent);
            }
        });
    }
}
