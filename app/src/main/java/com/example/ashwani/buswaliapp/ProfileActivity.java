package com.example.ashwani.buswaliapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    TextView username;
    TextView name, phone;
    Button logOut;
    DatabaseReference dbref, dbUser;
    FirebaseDatabase inst;
    String id;
    user currUser;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof);

        inst = FirebaseDatabase.getInstance();
        dbref = inst.getReference("MAIN");
        dbUser = dbref.child("USER");

        //username is mail
        username = findViewById(R.id.profile_username);
        name = findViewById(R.id.profile_dis_name);
        phone = findViewById(R.id.profile_phone);
        lv = findViewById(R.id.profile_prevBookings);

        id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        dbUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    user curr = dsp.getValue(user.class);
                    if (id.equals(curr.id)) {
                        currUser = curr;
                        Toast.makeText(ProfileActivity.this, curr.email, Toast.LENGTH_SHORT).show();
                        username.setText(curr.email);
                        phone.setText(curr.phoneNumber);
                        name.setText(curr.name);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
