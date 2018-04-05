package com.example.ashwani.buswaliapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class passShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_show);
        TextView tv=findViewById(R.id.passValid_tv);
        tv.setText(staticClass.passMonth);
    }
}
