package com.example.dhairyapujara.beauty_hub;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class Dashboard extends ListActivity  {
    TextView tv;
    String classes[]  = {"PriceFilter","Filter","ImageUpload", "ExpandableList","BrandFilter","DisplayProduct","GridLayoutActivity","SideMenu","NavDrawer","Login1","Menu","Add_User","Logout","AndroidDashboardDesignActivity","View_Products","ViewProducts","SearchBar"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Intent i = getIntent();
        String username = i.getStringExtra(login.USER_NAME);
        tv = (TextView)findViewById(R.id.welcome);
        tv.setText("Welcome "+username);*/
        setListAdapter(new ArrayAdapter<String>(Dashboard.this,android.R.layout.simple_list_item_1,classes));



    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String butter = classes[position];
        try {
            Class ourClass = Class.forName("com.example.dhairyapujara.beauty_hub."+butter);
            Intent i = new Intent(Dashboard.this,ourClass);
            startActivity(i);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
