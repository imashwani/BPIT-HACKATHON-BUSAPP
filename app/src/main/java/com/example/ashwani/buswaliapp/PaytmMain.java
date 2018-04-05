package com.example.ashwani.buswaliapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

public class PaytmMain extends AppCompatActivity {
    EditText mRupee_tv;
    EditText msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paytm_main);


        msg = findViewById(R.id.msg_home); Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar mActionBar = getSupportActionBar();
//        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater li = LayoutInflater.from(this);
        RelativeLayout relativeLayout = findViewById(R.id.top);
        View customView = li.inflate(R.layout.custon_app_bar, relativeLayout, false);
        mActionBar.setCustomView(customView);
        mActionBar.setDisplayShowCustomEnabled(true);
//        ImageButton addContent = (ImageButton)    customView.findViewById(R.id.action_add);


        mRupee_tv = findViewById(R.id.money);
        mRupee_tv.setText(getIntent().getStringExtra("money"));


        Context context = getBaseContext();
        Drawable drawable = getSymbol(context, "\u20B9"/*Your Symbol*/, mRupee_tv.getTextSize(), mRupee_tv.getCurrentTextColor());
        mRupee_tv.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);

        ImageView imageView = findViewById(R.id.pay);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itapna = new Intent(PaytmMain.this, TransferDetails.class);
                String amnt = mRupee_tv.getText().toString();

                String typo = msg.getText().toString();
                itapna.putExtra("money", amnt);
                itapna.putExtra("data", typo);
                if (amnt.length() != 0)
                    startActivity(itapna);
                else
                    Toast.makeText(PaytmMain.this, "paise?", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public Drawable getSymbol(Context context, String symbol, float textSize, int color) {
        Paint paint = new Paint(ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(color);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(symbol) + 0.5f); // round
        int height = (int) (baseline + paint.descent() + 0.5f);
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(image);
        canvas.drawText(symbol, 0, baseline, paint);
        return new BitmapDrawable(context.getResources(), image);
    }
    }

