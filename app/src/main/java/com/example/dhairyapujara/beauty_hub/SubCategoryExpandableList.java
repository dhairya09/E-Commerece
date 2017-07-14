package com.example.dhairyapujara.beauty_hub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import adapter.CustomGridViewAdapter;
import adapter.ExpandableListAdapter;

public class SubCategoryExpandableList extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;

    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    static ArrayList<String> subsub_arr = new ArrayList<String>();
    private Toolbar mToolbar;

    String gender,category;
    int m=0;

    /*JSONObject JSONSubCategory = new JSONObject();
    JSONObject JSONSubSubCategory = new JSONObject();
    JSONObject EverythingJSON = new JSONObject();*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category_expandable_list);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle b = getIntent().getExtras();
        final String res = b.getString("data");
        Log.d("Result_dp", res);

        String y[] = res.split("  ");


        gender = y[1];
        category = y[2];


        Log.d("bi_Gender", gender);
        Log.d("bi_Category",category);


        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        DisplaySubCat(res);

        expListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                Toast.makeText(getApplicationContext(),
                        "Group Clicked " + listDataHeader.get(groupPosition),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                String sub_cat = listDataHeader.get(groupPosition);
                String sub_sub_cat = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);

                String res1 = res;

                Log.d("dp_sc",sub_cat);
                Log.d("dp_ssc",sub_sub_cat);
                //MenCat(gen, cat);
                Item(sub_cat, sub_sub_cat,gender,category,res1);
                return false;
            }
        });








    }

    private void Item(final String sub_cat, final String sub_sub_cat, final String gender,final String category, final String res1)
    {
        Log.d("dp1_sc",sub_cat);
        Log.d("dp1_ssc",sub_sub_cat);
        Log.d("dp1_gen",gender);
        Log.d("dp1_cat",category);




        class Items extends AsyncTask<String,Void,String>
        {
            /*ProgressDialog loading;
            @Override
            protected void onPreExecute()
            {
                loading = ProgressDialog.show(getApplicationContext(),"Loading","Wait",true,true);
                super.onPreExecute();
            }*/
            @Override
            protected String doInBackground(String... strings)
            {
                String url = strings[0];
                HashMap<String,String> params = new HashMap<String,String>();
                params.put(Config.KEY_EMP_GNAME,gender);
                params.put(Config.KEY_EMP_CNAME,category);
                params.put(Config.KEY_EMP_SCNAME,sub_cat);
                params.put(Config.KEY_EMP_SSCNAME,sub_sub_cat);
                RequestHandler rh = new RequestHandler();
                String resu = rh.sendPostRequest(url, params);
                return resu;
            }
            @Override
            protected void onPostExecute(String s)
            {
                /*if(loading.isShowing())
                    loading.dismiss();*/
                super.onPostExecute(s);
                Log.d("redshirt",s);

                ViewItems(s,sub_cat,sub_sub_cat,res1);


            }

        }

        Items is = new Items();
        is.execute(Config.URL_GET_ITEM_BASED_ON_SUBSUBCAT);
    }

    private void ViewItems(String s,String sub_cat,String sub_sub_cat,String res1)
    {
        String pass1 = null;
        /*putinJSON(JSONSubCategory,sub_cat);
        putinJSON(JSONSubSubCategory, sub_sub_cat);
        try {
            EverythingJSON.put("SubCategory", JSONSubCategory);
            EverythingJSON.put("SubSubCategory", JSONSubSubCategory);

        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        pass1 = ""+s+"  "+res1+"  "+sub_cat+"  "+sub_sub_cat;
        Log.d("Mrudu", pass1);
        Intent i = new Intent(getApplicationContext(), DisplayItem.class);
        i.putExtra("data",pass1);
        startActivity(i);
    }

    /*private void putinJSON(JSONObject json, String value)
    {
        try {
            json.put("key",value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/

    private void DisplaySubCat(String res)
    {


        JSONArray jaar = null;
        try {
            JSONObject json = new JSONObject(res);





            jaar  = json.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i=0;i<jaar.length();i++)
            {

                JSONObject c = jaar.getJSONObject(i);
                String sc_name = c.getString(Config.TAG_SUBCAT);

                DisplaySubSubCat(sc_name);




            }





        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void DisplaySubSubCat(final String sc_name)
    {
        listDataHeader.add(sc_name);
        Log.d("DataHeader:", String.valueOf(listDataHeader));
        class SubSubCat extends AsyncTask<String,Void,String>
        {
            /*ProgressDialog loading;
            @Override
            protected void onPreExecute()
            {
                loading = ProgressDialog.show(getApplicationContext(),"Loading","Plz Wait",true,true);
                super.onPreExecute();
            }*/

            @Override
            protected String doInBackground(String... strings) {

                String url = strings[0];
                HashMap<String,String> params = new HashMap<String,String>();
                params.put(Config.KEY_EMP_SCNAME,sc_name);
                RequestHandler rh = new RequestHandler();
                String result = rh.sendPostRequest(url,params);
                return result;
            }

            @Override
            protected void onPostExecute(String r)
            {
                super.onPostExecute(r);
                Log.d("result123",r);
                /*Dialog d = new Dialog(getApplicationContext());
                TextView tv = new TextView(getApplicationContext());
                d.setTitle("hacked");
                tv.setText(r);
                d.setContentView(tv);
                d.show();*/
                ViewSubSubCategory(r);


            }
        }
        SubSubCat op = new SubSubCat();
        op.execute(Config.URL_GET_SUBSUBCAT_BASED_ON_SUBCAT);
    }

    private void ViewSubSubCategory(String r)
    {
            JSONArray jaar = null;
            subsub_arr.clear();


        try {
            JSONObject j = new JSONObject(r);
            jaar = j.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int k=0;k<jaar.length();k++)
            {
                JSONObject c = jaar.getJSONObject(k);
                String subsub_cat = c.getString(Config.TAG_SUBSUBCAT);
                subsub_arr.add(subsub_cat);
            }
            Log.d("child_id", String.valueOf(m));
            Log.d("subsub_arr", String.valueOf(subsub_arr));
            Log.d("In_He", listDataHeader.get(m));


            listDataChild.put(listDataHeader.get(m),subsub_arr);
            Log.d("DataChild:", String.valueOf(listDataChild));
            m++;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
    }


}
