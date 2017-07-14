package com.example.dhairyapujara.beauty_hub;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class SoundStuff extends Activity implements View.OnClickListener, View.OnLongClickListener {
    View v;
    SoundPool sp;
    int explosion = 0;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        v = new View(this);

        v.setOnClickListener(this);
        v.setOnLongClickListener(this);
        setContentView(v);
        sp = new SoundPool(5,AudioManager.STREAM_MUSIC,0);
        explosion = sp.load(this,R.drawable.bh2_splash,1);
        mp = MediaPlayer.create(this,R.drawable.bh2_splash);

    }


    @Override
    public void onClick(View view) {
        /*if(explosion!=0)
        sp.play(explosion,1,1,0,0,1);*/
    }

    @Override
    public boolean onLongClick(View view) {
        mp.start();
        return false;
    }
}
