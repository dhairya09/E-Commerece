package com.example.dhairyapujara.beauty_hub;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ViewFlipper;


public class Flipper extends Activity implements View.OnClickListener {

    ViewFlipper flippy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flip);
        flippy = (ViewFlipper)findViewById(R.id.viewFlipper);
        flippy.setOnClickListener(this);
        flippy.setFlipInterval(1000);
        flippy.startFlipping();
    }


    @Override
    public void onClick(View view) {
        flippy.showNext();
    }
}
