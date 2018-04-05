package com.example.ashwani.buswaliapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    user userObj;
    int RC_SIGN_IN = 1;
    CardView busPass, game, ticketBook;
    DatabaseReference dbref, dbUser;
    FirebaseDatabase inst;
    private String mUsername = null;
    private String mUseremail = null, phone = null;
    private TextView tv;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private TextView nav_user, nav_mail;
    private DrawerLayout mDrawerLayout;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        inst = FirebaseDatabase.getInstance();
        dbref = inst.getReference("MAIN");
        dbUser = dbref.child("USER");

        busPass = findViewById(R.id.busPass_home);
        game = findViewById(R.id.palygame_home);
        ticketBook = findViewById(R.id.bookTicket_home);
        nav_mail = findViewById(R.id.nav_emailuser);
        nav_user = findViewById(R.id.nav_username);

        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, GameActivity.class);
                startActivity(i);
            }
        });

        busPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent i = new Intent(HomeActivity.this, Buspass.class);

                    startActivity(i);


            }
        });


        ticketBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(staticClass.ticket==true){
                    Toast.makeText(HomeActivity.this,"You already have a Ticket",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(HomeActivity.this, ticketAct.class);
                    startActivity(i);
                }
                else{
                Intent i = new Intent(HomeActivity.this, barScan.class);
                startActivity(i);
            }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //------------------------------------------------
        mFirebaseAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                 user = firebaseAuth.getCurrentUser();
                if (user != null) {
//                    Toast.makeText(HomeActivity.this, "You are logged in friend", Toast.LENGTH_SHORT).show();
                    mUsername = user.getDisplayName();
                    mUseremail = user.getEmail();
                    setUserdata();
                    android.util.Log.d("", "onAuthStateChanged: CCIN Email:" + mUseremail + "name: " + mUsername);

                } else {//user signed out
                    startActivityForResult(
                            com.firebase.ui.auth.AuthUI.getInstance()
                                    .createSignInIntentBuilder().setLogo(R.drawable.download)
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(
                                            Arrays.asList(new com.firebase.ui.auth.AuthUI.IdpConfig.EmailBuilder().build(),
                                                    new com.firebase.ui.auth.AuthUI.IdpConfig.GoogleBuilder().build()
                                            ))
                                    .build(),
                            RC_SIGN_IN);
                }

            }
        };

        //-----------------------------------------------


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
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
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
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home_navbar) {
            Intent i = new Intent(HomeActivity.this, HomeActivity.class);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.profile_navbar) {
            Intent i = new Intent(HomeActivity.this, ProfileActivity.class);
            startActivity(i);
        }
        else if (id == R.id.passAct) {
            if(staticClass.ticket==true)
            {
                Intent i = new Intent(HomeActivity.this, ticketAct.class);
                startActivity(i);
            }
            else{
                Toast.makeText(HomeActivity.this, "You don't have any Ticket yet!", Toast.LENGTH_SHORT).show();
            }

        } else if (id == R.id.logout_navbar) {
            FirebaseAuth.getInstance().signOut();
        } else if (id == R.id.nav_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "This is the new hassale free app to book tickets. Try this app.");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }  else if (id == R.id.nav_send) {
            //TODO: github website link here
            String url = "http://www.example.com";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setUserdata() {
        user u = new user();
        u.email = mFirebaseAuth.getCurrentUser().getEmail();
        u.name = mFirebaseAuth.getCurrentUser().getDisplayName();
        TextView uname=findViewById(R.id.nav_username);
        TextView uemail=findViewById(R.id.nav_emailuser);
//        uname.setText(user.getDisplayName());
//        uemail.setText(user.getEmail());

        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        u.id = id;
        dbUser.child(id).setValue(u);
        staticClass.u=u;
//        nav_user.setText(u.name);
//        nav_mail.setText(u.email);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthStateListener != null)
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }

}
