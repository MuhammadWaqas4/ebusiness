package com.example.foji.e_business;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Investors extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView uploadIcon;
    TextView uploadText,textwelcomeID;
    LinearLayout downloadlayout, chatIn, partnershipIn, entrepreneurlist;
    Button log;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investors);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


       // mAuth = FirebaseAuth.getInstance();
       /* if (mAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(getApplicationContext(), Login.class));
        }*/
      //  FirebaseUser user = mAuth.getCurrentUser();

        textwelcomeID=findViewById(R.id.textwelcomeID);
    //    textwelcomeID.setText("Welcome "+user.getEmail());

        chatIn = findViewById(R.id.chatID);
        chatIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:"));
                startActivity(sendIntent);
            }
        });

        partnershipIn = findViewById(R.id.ipayments);
        partnershipIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Investors.this, Payment.class));
            }
        });
        downloadlayout=findViewById(R.id.downloadProposalID);
        downloadlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Investors.this,MyRecyclerViewActivity.class));
               // finish();
            }
        });
        entrepreneurlist = findViewById(R.id.EnlistID);
        entrepreneurlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent entrepreneur = new Intent(Investors.this, Entrepreneurlist.class);
                startActivity(entrepreneur);
                //finish();
            }
        });

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.investors, menu);
        return true;
    }

 /*   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            //startActivity(new Intent(Navigation.this, Navigation.class));
        } else if (id == R.id.nav_contactus) {
            startActivity(new Intent(Investors.this, Contact.class));
        } else if (id == R.id.nav_aboutus) {
            startActivity(new Intent(Investors.this, About.class));
        } else if (id == R.id.nav_help) {
            startActivity(new Intent(Investors.this, Help.class));

        } else if (id == R.id.nav_logout) {
           // mAuth.signOut();
            finish();
            startActivity(new Intent(getApplicationContext(), Login.class));
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
