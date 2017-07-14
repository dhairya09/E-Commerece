package com.example.dhairyapujara.beauty_hub;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;




public class Splash extends Activity {
    MediaPlayer song;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        //song = MediaPlayer.create(Splash.this,R.drawable.luv);
        /*SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean music = getPrefs.getBoolean("checkbox",true);
        if(music == true){
            song.start();
        }*/


        Thread timer = new Thread(){
            public void run(){
                    try{
                        sleep(5000);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    finally {
                        Intent in = new Intent(Splash.this, Dashboard.class);
                        startActivity(in);
                    }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
        song.release();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }


}
