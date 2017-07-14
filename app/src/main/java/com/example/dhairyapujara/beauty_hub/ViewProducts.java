package com.example.dhairyapujara.beauty_hub;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class ViewProducts extends Activity implements View.OnClickListener {



    LinearLayout l,l2;
    final CheckBox[] checkboxbuttons = new CheckBox[50];
    int id= 1;
    ScrollView sv;
    ArrayList<String> selchkboxCategory;
    ArrayList<String> selchkboxBrand;
    ArrayList<String> selchkboxGender;
    ArrayList<String> selchkboxItem;

    Button products;

    JSONObject JSONGender = new JSONObject();
    JSONObject JSONBrand = new JSONObject();
    JSONObject JSONCategory = new JSONObject();
    JSONObject JSONItem = new JSONObject();

    JSONArray j = new JSONArray();

    JSONObject EverythingJSON = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_products);

        selchkboxCategory = new ArrayList<String>();
        selchkboxBrand = new ArrayList<String>();
        selchkboxGender = new ArrayList<String>();
        selchkboxItem = new ArrayList<String>();



        //l = (LinearLayout)findViewById(R.id.vish);
        l2 = (LinearLayout)findViewById(R.id.vish);

        products = new Button(this);
        l2.addView(products);
        products.setText("View Products");
        products.setGravity(Gravity.CENTER);
        products.setTextColor(Color.BLACK);
        products.setId(R.id.id);
        products.setOnClickListener(this);





        getGender();
        getCategory();
        getBrand();
        getItem();



    }

    private void getItem()
    {
        class Item extends AsyncTask<String,Void,String>
        {

            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();

            }
            @Override
            protected void onPostExecute(String s)
            {
                super.onPostExecute(s);
                Log.d("items",s);
                showItem(s);

            }
            @Override
            protected String doInBackground(String... strings) {
                String url = strings[0];
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(url);

                return s;
            }
        }
        Item i = new Item();
        i.execute(Config.URL_GET_ALL_ITEM);
    }

    private void showItem(String s)
    {
        id=0;
        JSONArray jarr = null;
        try {
            JSONObject json = new JSONObject(s);
            jarr = json.getJSONArray(Config.TAG_JSON_ARRAY);

            TextView et4 = new TextView(getBaseContext());
            l2.addView(et4);
            et4.setText("Items Name");
            et4.setGravity(Gravity.LEFT);
            et4.setTextColor(Color.BLACK);

            for(int i=0;i<jarr.length();i++)
            {
                JSONObject c = jarr.getJSONObject(i);
                String name =  c.getString(Config.TAG_NAME);



                checkboxbuttons[i]  = new CheckBox(ViewProducts.this);
                checkboxbuttons[i].setId(i+1);
                l2.addView(checkboxbuttons[i]);
                checkboxbuttons[i].setText(name);
                checkboxbuttons[i].setTextColor(Color.GREEN);

                //String chk = Integer.toString(checkboxbuttons[i].getId());
                //selchkboxItem.add(chk);
                checkboxbuttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String chk = null;
                        CheckBox cb = (CheckBox) v;
                        if (((CheckBox) v).isChecked()) {
                            chk = Integer.toString(v.getId());

                            selchkboxItem.add(chk);
                            //Toast.makeText(ViewProducts.this, "Selected CheckBox ID" + v.getId(), Toast.LENGTH_SHORT).show();
                        }
                        /*else
                        {

                                selchkboxGender.remove(chk);
                        }*/
                    }
                });

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void getBrand()
    {
        class Brand extends AsyncTask<String,Void,String>
        {

            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();

            }
            @Override
            protected void onPostExecute(String s)
            {
                super.onPostExecute(s);
                Log.d("Brands",s);
                showBrand(s);

            }
            @Override
            protected String doInBackground(String... strings) {
                String url = strings[0];
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(url);

                return s;
            }
        }
        Brand b = new Brand();
        b.execute(Config.URL_GET_ALL);
    }

    private void showBrand(String s)
    {
        id=0;
        JSONArray jarr = null;
        try {
            JSONObject json = new JSONObject(s);
            jarr = json.getJSONArray(Config.TAG_JSON_ARRAY);

            TextView et2 = new TextView(getBaseContext());
            l2.addView(et2);
            et2.setText("Brand Name");
            et2.setGravity(Gravity.LEFT);
            et2.setTextColor(Color.BLACK);
            for(int i=0;i<jarr.length();i++)
            {
                JSONObject c = jarr.getJSONObject(i);
                String name =  c.getString(Config.TAG_NAME);
                checkboxbuttons[i]  = new CheckBox(ViewProducts.this);
                checkboxbuttons[i].setId(i+1);
                l2.addView(checkboxbuttons[i]);
                //checkboxbuttons[i].setOnCheckedChangeListener(this);
                checkboxbuttons[i].setText(name);
                checkboxbuttons[i].setTextColor(Color.DKGRAY);

                //String chk = Integer.toString(checkboxbuttons[i].getId());
                //selchkboxBrand.add(chk);
                checkboxbuttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String chk = null;
                        CheckBox cb = (CheckBox) v;
                        if (((CheckBox) v).isChecked()) {
                            chk = Integer.toString(v.getId());

                            selchkboxBrand.add(chk);
                            //Toast.makeText(ViewProducts.this, "Selected CheckBox ID" + v.getId(), Toast.LENGTH_SHORT).show();
                        }
                        /*else
                        {

                                selchkboxGender.remove(chk);
                        }*/
                    }
                });
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getCategory() {
        class Category extends AsyncTask<String,Void,String>
        {

            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();

            }
            @Override
            protected void onPostExecute(String s)
            {
                super.onPostExecute(s);
                Log.d("Categories",s);
                showCategory(s);

            }
            @Override
            protected String doInBackground(String... strings) {
                String url = strings[0];
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(url);

                return s;
            }
        }
        Category c = new Category();
        c.execute(Config.URL_GET_ALL_CAT);
    }

    private void showCategory(String s)
    {
        id=0;
        JSONArray jarr = null;
        try {
            JSONObject json = new JSONObject(s);
            jarr = json.getJSONArray(Config.TAG_JSON_ARRAY);

            TextView et1 = new TextView(getBaseContext());
            l2.addView(et1);
            et1.setText("Category Name");
            et1.setGravity(Gravity.LEFT);
            et1.setTextColor(Color.BLACK);

            for(int i=0;i<jarr.length();i++)
            {
                JSONObject c = jarr.getJSONObject(i);
                String name =  c.getString(Config.TAG_NAME);


                checkboxbuttons[i]  = new CheckBox(ViewProducts.this);
                checkboxbuttons[i].setId(i+1);
                l2.addView(checkboxbuttons[i]);
                checkboxbuttons[i].setText(name);
                checkboxbuttons[i].setTextColor(Color.BLUE);

                //String chk = Integer.toString(checkboxbuttons[i].getId());
                //selchkboxCategory.add(chk);

                checkboxbuttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String chk = null;
                        CheckBox cb = (CheckBox) v;
                        if (((CheckBox) v).isChecked()) {
                            chk = Integer.toString(v.getId());

                            selchkboxCategory.add(chk);
                            //Toast.makeText(ViewProducts.this, "Selected CheckBox ID" + v.getId(), Toast.LENGTH_SHORT).show();
                        }
                        /*else
                        {

                                selchkboxGender.remove(chk);
                        }*/
                    }
                });
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    private void getGender()
    {
        class Gender extends AsyncTask<String,Void,String>
        {

            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();

            }
            @Override
            protected void onPostExecute(String s)
            {
                super.onPostExecute(s);
                Log.d("gender",s);
                showGender(s);

            }
            @Override
            protected String doInBackground(String... strings) {
                String url = strings[0];
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(url);

                return s;
            }
        }
        Gender g = new Gender();
        g.execute(Config.URL_GET_ALL_GEN);
    }

    private void showGender(String str)
    {

        JSONArray jarr = null;
        try {
            JSONObject json = new JSONObject(str);
            jarr = json.getJSONArray(Config.TAG_JSON_ARRAY);

            TextView et = new TextView(getBaseContext());
            l2.addView(et);
            et.setText("Gender Name");
            et.setGravity(Gravity.LEFT);
            et.setTextColor(Color.BLACK);

            for(int i=0;i<jarr.length();i++)
            {
                JSONObject c = jarr.getJSONObject(i);
                String name =  c.getString(Config.TAG_GENDER_NAME);
                checkboxbuttons[i]  = new CheckBox(ViewProducts.this);
                checkboxbuttons[i].setId(i + 3);
                l2.addView(checkboxbuttons[i]);
                checkboxbuttons[i].setText(name);

                checkboxbuttons[i].setTextColor(Color.RED);

                //String chk = Integer.toString(checkboxbuttons[i].getId());
                //selchkboxGender.add(chk);

                checkboxbuttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override


                    public void onClick(View v) {
                        String chk = null;
                        CheckBox cb = (CheckBox) v;
                        if (((CheckBox) v).isChecked()) {
                             chk = Integer.toString(v.getId());

                            selchkboxGender.add(chk);
                            //Toast.makeText(ViewProducts.this, "Selected Gender  ID" + v.getId(), Toast.LENGTH_SHORT).show();
                        }
                        /*else
                        {
                            selchkboxGender.remove(chk);

                        }*/
                    }
                });

            }
            /*Dialog d = new Dialog(ViewProducts.this);
            TextView tv = new TextView(ViewProducts.this);
            tv.setText((CharSequence) selchkboxGender);
            d.setTitle("hacked");
            d.setContentView(tv);
            d.show();*/
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_products, menu);
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


        /*final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selected Gender ID");
         AlertDialog alert;*/

        /*builder.setItems(selchkboxGender, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection
                alert.dismiss();
            }
        });*/
        /*alert = builder.create();
        alert.show();
        putinJSON(JSONGender, selchkboxGender);*/
        /*putinJSON(JSONCategory,selchkboxCategory);
        putinJSON(JSONBrand, selchkboxBrand);
        putinJSON(JSONItem, selchkboxItem);*/

        /*try {
            EverythingJSON.put("Gen", JSONGender);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
            /*EverythingJSON.put("Category", JSONCategory);
            EverythingJSON.put("Brand", JSONBrand);
            EverythingJSON.put("Item",JSONItem);*/

        Log.d("Only GIds", String.valueOf(selchkboxGender));
        Log.d("Only CIds", String.valueOf(selchkboxCategory));
        Log.d("Only BIds", String.valueOf(selchkboxBrand));
        Log.d("Only IIds", String.valueOf(selchkboxItem));
        putinJSON(JSONGender, selchkboxGender);
        putinJSON(JSONCategory, selchkboxCategory);
        putinJSON(JSONBrand, selchkboxBrand);
        putinJSON(JSONItem, selchkboxItem);
        Log.d("Gender Ids", String.valueOf(JSONGender));
        Log.d("Category Ids", String.valueOf(JSONCategory));
        Log.d("Brands Ids", String.valueOf(JSONBrand));
        Log.d("Items Ids", String.valueOf(JSONItem));
        try {
            EverythingJSON.put("Gender",JSONGender);
            EverythingJSON.put("Category",JSONCategory);
            EverythingJSON.put("Brand",JSONBrand);
            EverythingJSON.put("Item",JSONItem);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        j.put(EverythingJSON);
        Toast.makeText(ViewProducts.this,""+j,Toast.LENGTH_LONG).show();
        Log.d("Entire", String.valueOf(j));
        viewtable(j);
        /*try {
            EverythingJSON.put("Gender", JSONGender);
            viewtable(EverythingJSON);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/





    }

    private void viewtable(final JSONArray everythingJSON)
    {

        class viewtbl extends AsyncTask<String,Void,String>
        {
            ProgressDialog loading;



            @Override
            protected String doInBackground(String... strings) {

                String url = strings[0];
                ArrayList<NameValuePair> postvars = new ArrayList<NameValuePair>();
                postvars.add(new BasicNameValuePair("JSON",String.valueOf(everythingJSON)));
                ServiceHandler sh = new ServiceHandler();
                String result = sh.makeServiceCall(url,2,postvars);
                return result;

            }

            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewProducts.this,"Loading","Plz Wait",true,true);
            }


            protected void onPostExecute(String res)
            {
                super.onPostExecute(res);
                if(loading.isShowing())
                    loading.dismiss();
                Log.d("After Checkbox:", res);
                /*Dialog d = new Dialog(ViewProducts.this);
                d.setTitle("hacked");
                TextView tv = new TextView(ViewProducts.this);
                tv.setText(res);

                d.setContentView(tv);
                d.show();*/
                DisplayItems(res);
            }


        }
        viewtbl vw = new viewtbl();
        vw.execute(Config.URL_VIEW_PRODUCTS);

    }

    private void DisplayItems(String res)
    {
        Intent i = new Intent(getApplicationContext(),DisplayProducts.class);
        i.putExtra("data",res);
        startActivity(i);

    }

    private void putinJSON(JSONObject json, ArrayList<String> selchkbox)
    {
        Log.d("Size", Integer.toString(selchkbox.size()));
        for (int i = 0; i < selchkbox.size(); i++) {

            try {
                //jsonGender.put("Count:"+String.valueOf(i+1),selchkboxGender.get(i));
                json.put("Count" + String.valueOf(i+1), selchkbox.get(i));

                //jsonGender.put("Count:" + String.valueOf(i + 1), selchkboxGender.get(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }



    }


}
