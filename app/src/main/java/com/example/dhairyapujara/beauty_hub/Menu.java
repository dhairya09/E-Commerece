package com.example.dhairyapujara.beauty_hub;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Dhairya Pujara on 1/23/2016.
 */
public class Menu extends ListActivity {
    String classes[] = { "Gender","Categories","Brands","Items","ImageUpload","ImageFetchAll","ViewProducts","View_Products"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, classes));
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String cheese = classes[position];
        try {
            Class ourClass = Class.forName("com.example.dhairyapujara.beauty_hub." + cheese);
            //Class ourClass = Class.forName(""+cheese);
            Intent in = new Intent(Menu.this, ourClass);
            startActivity(in);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mf = getMenuInflater();
        mf.inflate(R.menu.cool_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.About:
                Intent i = new Intent(Menu.this, About.class);
                startActivity(i);
                break;
            case R.id.Pref:
                Intent p = new Intent(Menu.this, Preference.class);
                startActivity(p);
                break;
            case R.id.exit:
                finish();
                            break;
        }
        return false;
    }
}

