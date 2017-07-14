package com.example.dhairyapujara.beauty_hub;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SlidingDrawer;


public class Slider extends Activity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener, SlidingDrawer.OnDrawerOpenListener {
    Button b1,b2,b3;
    CheckBox c1;
    SlidingDrawer sd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sliding);
        b1 = (Button)findViewById(R.id.btn1);
        b2 = (Button)findViewById(R.id.btn2);
        b3 = (Button)findViewById(R.id.btn3);
        c1 = (CheckBox)findViewById(R.id.cb);
        sd =(SlidingDrawer)findViewById(R.id.slidingD);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        c1.setOnCheckedChangeListener(this);
        sd.setOnDrawerOpenListener(this);



    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn1:sd.open();
                            break;
            case R.id.btn2:sd.close();
                            break;
            case R.id.btn3:sd.toggle();
                            break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(compoundButton.isChecked()){
                sd.lock();
        }else{
             sd.unlock();
        }
    }

    @Override
    public void onDrawerOpened() {
        MediaPlayer mp = MediaPlayer.create(this,R.drawable.bh2_splash);
    }
}
