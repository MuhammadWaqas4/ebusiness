package com.example.foji.e_business;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.foji.e_business.model.UserAdapter;
import com.example.foji.e_business.model.user;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Investorlist extends AppCompatActivity {

    //String TAG="TAG";
        private RecyclerView mRecyclerView;
        private UserAdapter mUserAdapter;
        private List<user> mDatalist;
        private ChildEventListener mChildeEventListner;


    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investorlist);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("Investor");

        mDatalist = new ArrayList<>();
        mRecyclerView=findViewById(R.id.user_recycleview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUserAdapter = new UserAdapter(this, mDatalist);
        mRecyclerView.setAdapter(mUserAdapter);

        mChildeEventListner= new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                user u = dataSnapshot.getValue(user.class);
                mDatalist.add(u);
                mUserAdapter.notifyDataSetChanged();
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
        mRef.addChildEventListener(mChildeEventListner);


    }
    protected void oneDestroy(){
        super.onDestroy();
        mRef.removeEventListener(mChildeEventListner);
    }

   /* public void readData(View view) {
        // mRef.addValueEventListener(new ValueEventListener()
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    Map<String, Object> data = (Map<String, Object>) snapshot.getValue();
                    Log.d(TAG, "onDataChange: Name: "+data.get("user"));
                    Log.d(TAG, "onDataChange: City: "+data.get("city"));
                    Log.d(TAG, "onDataChange: Cell: "+data.get("cell"));
                    Log.d(TAG, "onDataChange: Key: "+ snapshot.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: "+databaseError.getMessage());
            }
        });
    }*/
}
