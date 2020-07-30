package com.example.foji.e_business;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Payment extends AppCompatActivity {

    EditText title, cvcode, issue, expiry, digit,to;
    Button payment,chat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        title = findViewById(R.id.title);
        cvcode = findViewById(R.id.cvcodeID);
        issue = findViewById(R.id.issue);
        expiry = findViewById(R.id.expiry);
        to = findViewById(R.id.editText);
        digit = findViewById(R.id.digitcode);

        payment = findViewById(R.id.payment);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = digit.getText().toString();
                String pass = cvcode.getText().toString();
                if (validation() == true) {
                    if (digit.length() == 14 && cvcode.length() == 4 && to.length() == 14) {
                        Toast.makeText(Payment.this, "Payment Successfully done", Toast.LENGTH_SHORT).show();
                        title.setText("");
                        cvcode.setText("");
                        issue.setText("");
                        expiry.setText("");
                        digit.setText("");
                    } else Toast.makeText(Payment.this, "wrong info", Toast.LENGTH_SHORT).show();

                } else Toast.makeText(Payment.this, "wrong info", Toast.LENGTH_SHORT).show();
            }
        });
    }
        private boolean validation(){
        String titles, cvcodes, issues, expirys, digits, payments,tos;
        titles = title.getText().toString();
        cvcodes = cvcode.getText().toString();
        issues = issue.getText().toString();
        expirys = expiry.getText().toString();
        digits = digit.getText().toString();
        tos = to.getText().toString();
        payments = payment.getText().toString();
        if (TextUtils.isEmpty(titles)|| TextUtils.isEmpty(payments) || TextUtils.isEmpty(issues)||
                TextUtils.isEmpty(expirys)||TextUtils.isEmpty(cvcodes)||TextUtils.isEmpty(digits)||TextUtils.isEmpty(tos))
        {
            Toast.makeText(this, "Enter all fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;
    }
}

