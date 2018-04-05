package com.example.ashwani.buswaliapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Launcher extends AppCompatActivity {
    Handler h = new Handler();
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Launcher.this, HomeActivity.class);
                startActivity(i);
            }
        }, 3000);

        iv= (ImageView) findViewById(R.id.logo);
        iv.animate().rotationBy(360).scaleY(2).scaleX(2).setDuration(1500);
    }
}
