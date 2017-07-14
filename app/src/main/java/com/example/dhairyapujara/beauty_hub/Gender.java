package com.example.dhairyapujara.beauty_hub;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Gender extends Activity {
    Button b_gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);
        b_gender = (Button)findViewById(R.id.g_insert);
        b_gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent i = new Intent(Gender.this,Gender_Insert.class);
                startActivity(i);*/
            }
        });
    }


}
