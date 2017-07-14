package com.example.dhairyapujara.beauty_hub;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Filter extends TabActivity implements View.OnClickListener {

    JSONObject JSONGender = new JSONObject();
    JSONObject JSONBrand = new JSONObject();
    JSONObject JSONCategory = new JSONObject();
    JSONObject JSONSubCategory = new JSONObject();
    JSONObject JSONSubSubCategory = new JSONObject();

    JSONArray j = new JSONArray();

    JSONObject EverythingJSON = new JSONObject();




    private static final String BRAND = "Brand";
    private static final String PRICE = "Price";
    private static final String DISCOUNT = "Discount";

    String gender,category,subcat,subsubcat;
    Button b_apply,b_clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        b_apply = (Button)findViewById(R.id.apply);
        b_clear = (Button)findViewById(R.id.clear);

        /*Bundle b = getIntent().getExtras();
        String res6 = b.getString("data");*/
        //Log.d("Binzu", res6);

        /*String y[] = res6.split("  ");


        gender = y[2];
        category = y[3];
        subcat = y[4];
        subsubcat = y[5];

        Log.d("bi_Gender", gender);
        Log.d("bi_Category",category);
        Log.d("bi_subcat",subcat);
        Log.d("bi_subsubcat",subsubcat);*/

        b_apply.setOnClickListener(this);
        b_clear.setOnClickListener(this);






        TabHost tabHost = getTabHost();

        TabHost.TabSpec bra = tabHost.newTabSpec(BRAND);
        // Tab Icon
        bra.setIndicator(BRAND, getResources().getDrawable(R.drawable.icon_brand));
        Intent BrandIntent = new Intent(this,SearchBar.class);

        // Tab Content
        bra.setContent(BrandIntent);

        TabHost.TabSpec pri =  tabHost.newTabSpec(PRICE);
        pri.setIndicator(PRICE, getResources().getDrawable(R.drawable.icon_price));
        Intent PriceIntent = new Intent(this,PriceFilter.class);

        pri.setContent(PriceIntent);

        TabHost.TabSpec dis = tabHost.newTabSpec(DISCOUNT);
        dis.setIndicator(DISCOUNT, getResources().getDrawable(R.drawable.icon_discount));
        Intent DiscountIntent = new Intent(this,DiscountFilter.class);
        dis.setContent(DiscountIntent);

        tabHost.addTab(bra);
        tabHost.addTab(pri);
        tabHost.addTab(dis);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_filter, menu);
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
            /*switch (view.getId())
            {
                case R.id.apply:
                    Bundle bs = getIntent().getExtras();
                    String brands = bs.getString("dat");
                    Log.d("Brands_sur",brands);
                    try {
                        JSONGender.put("dh_gender",gender);
                        JSONCategory.put("dh_category",category);
                        JSONSubCategory.put("dh_subcat",subcat);
                        JSONSubSubCategory.put("dh_subsubcat",subsubcat);

                        //putinJSON(JSONBrand,brands);
                        //putinJSON(JSONPrice,filtered_price);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }





                    Log.d("dh_Gender Ids", String.valueOf(JSONGender));
                    Log.d("dh_Category Ids", String.valueOf(JSONCategory));
                    Log.d("dh_SubCategory Ids", String.valueOf(JSONSubCategory));
                    Log.d("dh_SubSubCategory Ids", String.valueOf(JSONSubSubCategory));
                    Log.d("dh_Brands Ids", String.valueOf(JSONBrand));

                    try {
                        EverythingJSON.put("Gender",JSONGender);
                        EverythingJSON.put("Category",JSONCategory);
                        EverythingJSON.put("SubCategory",JSONSubCategory);
                        EverythingJSON.put("SubSubCategory",JSONSubSubCategory);
                        EverythingJSON.put("Brand",JSONBrand);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    j.put(EverythingJSON);

                    Log.d("Entire_brand", String.valueOf(j));
                    viewtable(j);

                                break;
                case R.id.clear:
                                break;
            }*/
    }

    private void viewtable(final JSONArray everythingJSON)
    {

        class viewtbl extends AsyncTask<String,Void,String>
        {
            ProgressDialog loading;



            @Override
            protected String doInBackground(String... strings) {

                Log.d("harshu", String.valueOf(everythingJSON));
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
                loading = ProgressDialog.show(Filter.this,"Loading","Plz Wait",true,true);
            }


            protected void onPostExecute(String res)
            {
                super.onPostExecute(res);
                if(loading.isShowing())
                    loading.dismiss();
                Log.d("After Checkbox:", res);
                Dialog d = new Dialog(Filter.this);
                d.setTitle("hacked");
                TextView tv = new TextView(Filter.this);
                tv.setText(res);

                d.setContentView(tv);
                d.show();
                //DisplayItems(res);
            }


        }
        viewtbl vw = new viewtbl();
        vw.execute(Config.URL_VIEW_PRODUCTS);

    }


    private void putinJSON(JSONObject json, ArrayList<String> value)
    {
        for (int i = 0; i < value.size(); i++) {

            try {
                //jsonGender.put("Count:"+String.valueOf(i+1),selchkboxGender.get(i));
                json.put("Count" + String.valueOf(i+1), value.get(i));

                //jsonGender.put("Count:" + String.valueOf(i + 1), selchkboxGender.get(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
