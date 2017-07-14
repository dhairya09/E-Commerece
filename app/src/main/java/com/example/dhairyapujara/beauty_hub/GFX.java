package com.example.dhairyapujara.beauty_hub;

import android.app.Activity;
import android.content.Context;
import android.os.PowerManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class GFX extends Activity {

    MyBringBack ourview;
    PowerManager.WakeLock wl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

       PowerManager pM = (PowerManager)getSystemService(Context.POWER_SERVICE);
       wl =  pM.newWakeLock(PowerManager.FULL_WAKE_LOCK,"whatever");
        wl.acquire(5000);
        super.onCreate(savedInstanceState);

        ourview = new MyBringBack(this);
        setContentView(ourview);
    }

    @Override
    protected void onPause() {
        super.onPause();
        wl.release();
    }
}
