package com.example.ashwani.buswaliapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class GameActivity extends AppCompatActivity {
    CardView mm, clash, poke, royale, zombie, crick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mm = findViewById(R.id.minimilitia);
        clash = findViewById(R.id.clash);
        poke = findViewById(R.id.poke);
        royale = findViewById(R.id.royale);
        zombie = findViewById(R.id.zombie);
        crick = findViewById(R.id.cricket);

//        mm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(GameActivity.this, );
//                startActivity(i);
//            }
//        });
//
//        clash.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(GameActivity.this, );
//                startActivity(i);
//            }
//        });
//
//        poke.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(GameActivity.this, );
//                startActivity(i);
//            }
//        });
//
//        royale.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(GameActivity.this, );
//                startActivity(i);
//            }
//        });
//
//        zombie.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(GameActivity.this, );
//                startActivity(i);
//            }
//        });
//
//        crick.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(GameActivity.this, );
//                startActivity(i);
//            }
//        });

    }
}
