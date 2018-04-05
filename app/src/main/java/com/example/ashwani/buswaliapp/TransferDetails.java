package com.example.ashwani.buswaliapp;

import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TransferDetails extends AppCompatActivity {
    TextView mTransactionId_tv;
    TextView mRupee_tv, name, phone;
    TextView date_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_details);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_money);

        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//
        ImageView tick = findViewById(R.id.webview_image);
        Glide.with(TransferDetails.this).asGif()
                .load(R.drawable.tick)
                .into(tick);

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phoneNumber);
        mRupee_tv = findViewById(R.id.rupee);
        Intent it = getIntent();
        String amt = it.getStringExtra("money");
        if (amt.length() != 0)
            mRupee_tv.append(amt);

        String data = "";
        data = it.getStringExtra("data");

        date_tv = findViewById(R.id.dateTime);
        SimpleDateFormat ft = new SimpleDateFormat("hh:mm a, dd MMM yyyy");
        date_tv.setText(ft.format(new Date()));
        if (data != null) {
            if (data.length() > 0) {
                if ("med".equals(data) || data.equals("Med")) {
                    name.setText("Raja");
                    phone.setText("7678166846");
                }
                if ("egg".equals(data) || data.equals("Egg")) {
                    name.setText("Mukund");
                    phone.setText("9953239210");
                } else {
                    name.setText("Mukund");
                    phone.setText("9953239210");
                }
            }
        }

    }


    //--------------------------------------------

}
