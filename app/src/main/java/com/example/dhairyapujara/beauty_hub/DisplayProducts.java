package com.example.dhairyapujara.beauty_hub;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import adapter.CustomGridViewAdapter;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;



public class DisplayProducts extends ActionBarActivity {


    ArrayList<String> desc_arr = new ArrayList<String>();
    ArrayList<String> price_arr = new ArrayList<String>();
    GridView gv;
    CustomGridViewAdapter customGridAdapter;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.ic_home,
            R.drawable.ic_communities,
            R.drawable.ic_people
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displayproducts);

        gv = (GridView)findViewById(R.id.grid_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        Bundle b = getIntent().getExtras();
        String res = b.getString("data");
        Log.d("Result_dp", res);
        DisplayItems(res);












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
                String desc = c.getString(Config.TAG_PRICE);
                String price = c.getString(Config.TAG_DESC);
                desc_arr.add(desc);
                price_arr.add(price);

            }
            Log.d("Desc_dp", String.valueOf(desc_arr));
            Log.d("Price_dp", String.valueOf(price_arr));
            customGridAdapter = new CustomGridViewAdapter(this,desc_arr,price_arr,price_arr);
            gv.setAdapter(customGridAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setupTabIcons()
    {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
    }

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new FilterFragment(), "Filter");
        adapter.addFrag(new SortBy(),"SortBy");

        viewPager.setAdapter(adapter);

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mActivityList = new ArrayList<>();
        private final List<String> mActivityTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mActivityList.get(position);
        }

        @Override
        public int getCount() {
            return mActivityList.size();
        }
        public void addFrag(Fragment fragment,String title)
        {
            mActivityList.add(fragment);
            mActivityTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            return mActivityTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_products, menu);
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


}
