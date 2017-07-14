package com.example.dhairyapujara.beauty_hub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import activity.FragDrawer;
import adapter.CustomGridViewAdapter;

public class DisplayItem extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> desc_arr = new ArrayList<String>();
    ArrayList<String> price_arr = new ArrayList<String>();
    ArrayList<String> path_arr = new ArrayList<String>();
    GridView gv;
    CustomGridViewAdapter customGridAdapter;
    String res2;

    private Toolbar mToolbar;

    Button filter;
    Button SortBy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_item);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        gv = (GridView)findViewById(R.id.grid_view);

        Bundle b = getIntent().getExtras();
        res2 = b.getString("data");
        Log.d("Parthu", res2);
        DisplayItems(res2);

        filter = (Button)findViewById(R.id.fil);
        SortBy = (Button)findViewById(R.id.sort);


        filter.setOnClickListener(this);
        SortBy.setOnClickListener(this);
    }

    private void DisplayItems(String res)
    {
        Log.d("Result_dp1", res);

        JSONArray jaar = null;
        try {
            JSONObject json = new JSONObject(res);
            jaar  = json.getJSONArray(Config.TAG_JSON_ARRAY);
            Log.d("Size_dp", String.valueOf(jaar.length()));
            for(int i=0;i<jaar.length();i++)
            {
                JSONObject c = jaar.getJSONObject(i);
                String desc = c.getString(Config.TAG_DESC);
                String price = c.getString(Config.TAG_PRICE);
                String img_name = c.getString(Config.TAG_IMG_NAME);
                String img_path = c.getString(Config.TAG_IMG_PATH);
                String final_path = "http://192.168.43.157"+img_path+""+img_name;

                desc_arr.add(desc);
                price_arr.add(price);
                path_arr.add(final_path);

            }
            Log.d("Desc_dp", String.valueOf(desc_arr));
            Log.d("Price_dp", String.valueOf(price_arr));
            Log.d("Path_dp", String.valueOf(path_arr));
            customGridAdapter = new CustomGridViewAdapter(this,desc_arr,price_arr,path_arr);
            gv.setAdapter(customGridAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.fil:
                Intent i = new Intent(getApplicationContext(),FilterOption.class);
                i.putExtra("data",res2);
                startActivity(i);

                            break;
            case R.id.sort:
                            break;
        }
    }
}
