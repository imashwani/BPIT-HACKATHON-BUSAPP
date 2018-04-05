package com.example.ashwani.buswaliapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.Result;

import java.util.HashMap;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class barScan extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    DatabaseReference dbBus;
    FirebaseDatabase inst;
    public static final HashMap<String, String> hm = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inst = FirebaseDatabase.getInstance();
        dbBus = inst.getReference("BUSSTOP");

        fill();

        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        mScannerView.setTransitionName("Scan Bus Stop QRcode");
        setContentView(mScannerView);
    }

    private void fill() {
        hm.put("1", "KashMere Gate");
        hm.put("2", "Rithala");
        hm.put("3", "Kohat Enclave");
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
    boolean valid = true;

    @Override
    public void handleResult(Result rawResult) {
        Log.v("hello", rawResult.getText()); // Prints scan results
        Log.v("///hel2", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)
        res = rawResult.getText();
        Toast.makeText(this, res, Toast.LENGTH_SHORT).show();
//        dbBus.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Log.d("", "onDataChange: " + dataSnapshot);
//                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
//                    String st = (String) dsp.getKey().trim();
//                    if (res.equals(st)) {
//                        busStop = (String) dsp.getValue();
//                        valid = true;
//                    }
//                    Log.v("KEY FOUND---------", st);
//                    Log.d("", "onDataChange: ye hai value" + st);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        if (hm.containsKey(res)) {
            //*********incomplete
            Intent i = new Intent(barScan.this, DESTINATION_ACTIVITY.class);
            DESTINATION_ACTIVITY.choosen = hm.get(res);
//            i.putExtra("BUSSTOP", busStop);
            startActivity(i);
        } else {
            Toast.makeText(this, "Invalid QR Code ", Toast.LENGTH_SHORT).show();
        }
//        Intent i=new Intent(barScan.this,BookingActivity.class);
//        i.putExtra("busStop",rawResult.getText());
//        startActivity(i);

    }
}
