package com.example.foji.e_business;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class Updateaccount extends AppCompatActivity {

    String ikey;


//   // public Updateaccount(String ikey) {
//        this.ikey = ikey;
//    }

    Button button;
    EditText userName, city, cellNo;
    TextView text;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateaccount);

        userName = (EditText) findViewById(R.id.textnameID);
        city = (EditText) findViewById(R.id.textcityID);
        cellNo = (EditText) findViewById(R.id.textcellnoID);
        button = (Button) findViewById(R.id.updatebuttonID);
        text =(TextView)findViewById(R.id.keyID);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("Investor");

        Intent i = getIntent();
       final String pushkey = i.getStringExtra(Signup.KEY);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                text.setText(pushkey);

                ///// we can update it by sel value mathod too
//
                String name = userName.getText().toString();
                String city = userName.getText().toString();
                int num = Integer.parseInt(cellNo.getText().toString());

                Map<String, Object> insertValues = new HashMap<>();
                insertValues.put("user", name);
                insertValues.put("city",city);
                insertValues.put("cell",num);
                mRef.child(pushkey).setValue(insertValues);
//
            }
        });
    }

}
