package com.example.ashwani.buswaliapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    TextView username;
    TextView name, phone;
    Button logOut;
    DatabaseReference dbref, dbUser;
    FirebaseDatabase inst;
    String id;
    user currUser;
    ListView lv;
    public static ArrayList<String> al = new ArrayList<>();
    ArrayAdapter<String> adap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof);

        inst = FirebaseDatabase.getInstance();
        dbref = inst.getReference("MAIN");
        dbUser = dbref.child("USER");

        al.add("Kashmere Gate  --  Hauz Khas");
        al.add("Canaught place --  Hauz Khas");
        al.add("Kohat Enclave  --  Rithala");
        al.add("Kohat Enclave  --  NSP");
        al.add("Kashmere Gate  --  Hauz Khas");
        al.add("Kashmere Gate  --  Hauz Khas");

        //username is mail
        username = findViewById(R.id.profile_username);
        name = findViewById(R.id.profile_dis_name);
        phone = findViewById(R.id.profile_phone);
        lv = findViewById(R.id.profile_prevBookings);

        adap = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                al
        );
        lv.setAdapter(adap);

        id = FirebaseAuth.getInstance().getCurrentUser().getUid();

//        dbUser.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
//                    user curr = dsp.getValue(user.class);
//                    if (id.equals(curr.id)) {
//                        currUser = curr;
//                        Toast.makeText(ProfileActivity.this, curr.email, Toast.LENGTH_SHORT).show();
//                        username.setText(curr.email);
//                        phone.setText(curr.phoneNumber);
//                        name.setText(curr.name);
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        user curr = staticClass.u;
        username.setText(curr.email);
        phone.setText(curr.phoneNumber);
        name.setText(curr.name);

    }
}
