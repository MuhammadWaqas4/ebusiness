package com.example.foji.e_business;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView uploadIcon;
    TextView uploadText,textwelcomeID,emailview;
    LinearLayout uploadlayout, chatlayout, partnershiplayout, investorlistlayout;
    Button log;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      //  mAuth = FirebaseAuth.getInstance();

       /* Intent intent = getIntent();
        String message = intent.getStringExtra(Login.EXTRA_MESSAGE);
        TextView text = findViewById(R.id.welcomeID);
        text.setText(message);*/
     /*   Intent intent = getIntent();
        String message = intent.getStringExtra(Login.EXTRA_MESSAGE);
        emailview=findViewById(R.id.emailviewID);
        emailview.setText(message);*/


     chatlayout = findViewById(R.id.EchatID);
     chatlayout.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent sendIntent = new Intent(Intent.ACTION_VIEW);
             sendIntent.setData(Uri.parse("sms:"));
             startActivity(sendIntent);
         }
     });
     partnershiplayout = findViewById(R.id.LayoutpartnershipID);
     partnershiplayout.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             startActivity(new Intent(Navigation.this, Payment.class));
         }
     });
        uploadlayout = findViewById(R.id.LIDupload);
        chatlayout = findViewById(R.id.EchatID);
        partnershiplayout= findViewById(R.id.LayoutpartnershipID);
        investorlistlayout = findViewById(R.id.investorLayoutID);
        investorlistlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent investorList = new Intent(Navigation.this, Investorlist.class);
                startActivity(investorList);
                //finish();
            }
        });
        uploadIcon = (ImageView)findViewById(R.id.proposaluploadimg);
        uploadText = (TextView)findViewById(R.id.proposaluploadtext);
        textwelcomeID = (TextView)findViewById(R.id.textwelcomeID);
        //log = (Button)findViewById(R.id.logout);

        /*if (mAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(getApplicationContext(), Login.class));
        }*/
//        FirebaseUser user = mAuth.getCurrentUser();
  //   textwelcomeID.setText("Welcome "+user.getEmail());
       /* emailview=findViewById(R.id.emailviewID);
        TextView tv =findViewById(R.id.ebusinessID);
        tv.setText(user.getEmail());
        emailview.setText(user.getEmail());*/

      /*  log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });*/

        uploadlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Navigation.this, UploadProposal.class);
                startActivity(i);
            }
        });

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }
      /// side navigation handling three dots
   /* @Override
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
            startActivity(new Intent(Navigation.this, Contact.class));
        } else if (id == R.id.nav_aboutus) {
            startActivity(new Intent(Navigation.this, About.class));
        } else if (id == R.id.nav_help) {
            startActivity(new Intent(Navigation.this, Help.class));

        } else if (id == R.id.nav_logout) {
         //   mAuth.signOut();
            finish();
            startActivity(new Intent(getApplicationContext(), Login.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
