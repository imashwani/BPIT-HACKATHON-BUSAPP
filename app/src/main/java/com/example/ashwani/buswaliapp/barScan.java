package com.example.ashwani.buswaliapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class barScan extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    DatabaseReference dbBus;
    FirebaseDatabase inst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inst = FirebaseDatabase.getInstance();
        dbBus = inst.getReference("BUSSTOP");

        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        mScannerView.setTransitionName("Scan Bus Stop QRcode");
        setContentView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    String res = "", busStop = "";
    boolean valid = false;

    @Override
    public void handleResult(Result rawResult) {
        Log.v("hello", rawResult.getText()); // Prints scan results
        Log.v("///hel2", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)
        res = rawResult.getText();

        dbBus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("", "onDataChange: " + dataSnapshot);
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    String st = (String) dsp.getKey();
                    if (res.equals(st)) {
                        busStop = (String) dsp.getValue();
                        valid = true;
                    }
                    Log.d("", "onDataChange: ye hai value" + st);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        if (valid) {
            //*********incomplete
            Intent i = new Intent(barScan.this, DESTINATION_ACTIVITY.class);
            i.putExtra("BUSSTOP", busStop);
            startActivity(i);
        } else {
            Toast.makeText(this, "Invalid QR Code ", Toast.LENGTH_SHORT).show();
        }
//        Intent i=new Intent(barScan.this,BookingActivity.class);
//        i.putExtra("busStop",rawResult.getText());
//        startActivity(i);

    }
}
