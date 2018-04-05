package com.example.ashwani.buswaliapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DESTINATION_ACTIVITY extends AppCompatActivity {
    Spinner spinner;
    String origin = "";
public static String choosen="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination__activity);
//        origin = getIntent().getStringExtra("BUSSTOP");
        spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.destination_list, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ListView listView = findViewById(R.id.des_list);
                String x = getIntent().getStringExtra("busStop");
                x=adapterView.getItemAtPosition(i).toString();
                TextView tv = findViewById(R.id.des_from_to);
                tv.setText("Buses available to "+x);

                listView.setAdapter(new CustomAdapter(DESTINATION_ACTIVITY.this, new String[]{"BUS NO.", "764", "774", "874", "754", "964", "864"},
                        new String[]{"Fare", "15", "10", "15", "5", "25", "20"},
                        new String[]{"Category", "R", "G", "O", "R", "O", "O"}));
                Toast.makeText(DESTINATION_ACTIVITY.this, "Choose one of the above to get a ticket", Toast.LENGTH_LONG).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    class CustomAdapter extends ArrayAdapter<String> {

        String days[];
        String stime[];
        String etime[];
        public Activity activity;

        CustomAdapter(Activity activity, String days[], String stime[], String etime[]) {
            super(activity, R.layout.bus_layout, days);
            this.days = days;
            this.stime = stime;
            this.etime = etime;
            this.activity = activity;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = activity.getLayoutInflater();
            View v = inflater.inflate(R.layout.bus_layout, null);

            TextView t1 = v.findViewById(R.id.bus_name);
            TextView t2 = v.findViewById(R.id.bus_fare);
            TextView t3 = v.findViewById(R.id.bus_type);

            t1.setText(days[position]);
            t2.setText(stime[position]);
            t3.setText(etime[position]);
            if (position == 0) {

                t1.setTextColor(Color.WHITE);
                t2.setTextColor(Color.WHITE);
                t3.setTextColor(Color.WHITE);
                v.setBackgroundColor(Color.BLUE);
            } else {
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//TODO paytm

                    }
                });
            }
            return v;
        }
    }
}
