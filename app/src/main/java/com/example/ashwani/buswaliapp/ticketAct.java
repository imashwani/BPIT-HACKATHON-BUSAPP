package com.example.ashwani.buswaliapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ticketAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        if((staticClass.from.length()>0)){
            TextView id=findViewById(R.id.ticketid_tv);
            id.setVisibility(View.VISIBLE);
            TextView tv=findViewById(R.id.ticketFromTo_tv);
            tv.setText("You have a booked ticket from "+staticClass.from+" TO "+staticClass.to);
        }
        else {
            Toast.makeText(ticketAct.this,"You havent booked any ticket",Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
