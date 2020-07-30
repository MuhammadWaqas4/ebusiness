package com.example.foji.e_business;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.foji.e_business.model.UserAdapter;
import com.example.foji.e_business.model.user;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Entrepreneurlist extends AppCompatActivity {

    private RecyclerView mrecyclerView;
    private UserAdapter muserAdapter;
    private List<user> mdatalist;
    private ChildEventListener mchildeEventListner;

    private FirebaseDatabase mdatabase;
    private DatabaseReference mref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrepreneurlist);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mdatabase = FirebaseDatabase.getInstance();
        mref = mdatabase.getReference("Entrepreneur");

        mdatalist = new ArrayList<>();
        mrecyclerView=findViewById(R.id.Erecyclerview);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        muserAdapter = new UserAdapter(this, mdatalist);
        mrecyclerView.setAdapter(muserAdapter);
        mchildeEventListner= new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                user u = dataSnapshot.getValue(user.class);
                mdatalist.add(u);
                muserAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mref.addChildEventListener(mchildeEventListner);


    }
    protected void oneDestroy(){
        super.onDestroy();
        mref.removeEventListener(mchildeEventListner);
    }
}
