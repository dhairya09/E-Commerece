package com.example.dhairyapujara.beauty_hub;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Categories extends Activity implements View.OnClickListener {
    Button insert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);
        insert = (Button)findViewById(R.id.c_insert);
        insert.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent i = new Intent(Categories.this,CategoryInsert.class);
        startActivity(i);
    }
}
