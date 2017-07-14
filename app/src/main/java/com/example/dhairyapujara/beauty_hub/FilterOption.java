package com.example.dhairyapujara.beauty_hub;

import android.app.Application;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FilterOption extends TabActivity {

    private static final String BRAND = "Brand";
    private static final String PRICE = "Price";
    private static final String DISCOUNT = "Discount";
    String appen="";


    Button b_apply,b_clear;
    String gender,category,subcat,subsubcat;

    private static ArrayList<String> str = new ArrayList<>();
    private static ArrayList<String> str1 = new ArrayList<>();
    private static ArrayList<String> price_fill = new ArrayList<>();

    JSONObject JSONGender = new JSONObject();
    JSONObject JSONBrand = new JSONObject();
    JSONObject JSONCategory = new JSONObject();
    JSONObject JSONSubCategory = new JSONObject();
    JSONObject JSONSubSubCategory = new JSONObject();
    JSONObject JSONPrice = new JSONObject();

    JSONArray j = new JSONArray();

    JSONObject EverythingJSON = new JSONObject();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_option);

        b_apply = (Button)findViewById(R.id.apply);
        b_clear = (Button)findViewById(R.id.clear);

        Bundle b = getIntent().getExtras();
        String res6 = b.getString("data");
        Log.d("Binzu", String.valueOf(res6));

        String y[] = res6.split("  ");


        gender = y[2];
        category = y[3];
        subcat = y[4];
        subsubcat = y[5];

        Log.d("bi_Gender", gender);
        Log.d("bi_Category",category);
        Log.d("bi_subcat",subcat);
        Log.d("bi_subsubcat", subsubcat);


        b_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("trial", "gani");


                str = Choices.getBrands();
                Log.d("ques1", String.valueOf(str));


                str1 = Choices.getPrices();
                Log.d("ques2", String.valueOf(str1));

                for(int t=0;t<str1.size();t++)
                {
                    appen="";
                    String q = str1.get(t);
                    Log.d("yuki:",q);

                    Pattern pattern = Pattern.compile("\\w+([0-9]+)\\w+([0-9]+)");
                    Matcher matcher = pattern.matcher(q);
                    for(int i = 0 ; i < matcher.groupCount(); i++) {
                        matcher.find();
                        //System.out.println(matcher.group());

                        if(i!= matcher.groupCount()-1)
                        {
                             appen += matcher.group()+"-";
                        }
                        else
                        {
                            appen += matcher.group();
                        }
                    }
                    Log.d("appen",appen);
                    price_fill.add(appen);

                    /*String numberOnly= q.replaceAll("[^0-9]", "");
                    Log.d("fisool",numberOnly);*/
                }
                Log.d("price_fill", String.valueOf(price_fill));
                try {
                    JSONGender.put("dh_gender",gender);
                    JSONCategory.put("dh_category",category);
                    JSONSubCategory.put("dh_subcat",subcat);
                    JSONSubSubCategory.put("dh_subsubcat",subsubcat);

                    putinJSON(JSONBrand,str);
                    putinJSON(JSONPrice,price_fill);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    EverythingJSON.put("Gender",JSONGender);
                    EverythingJSON.put("Category",JSONCategory);
                    EverythingJSON.put("SubCategory",JSONSubCategory);
                    EverythingJSON.put("SubSubCategory",JSONSubSubCategory);
                    EverythingJSON.put("Brand",JSONBrand);
                    EverythingJSON.put("Price",JSONPrice);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                j.put(EverythingJSON);

                Log.d("LOL", String.valueOf(j));
                viewtable(j);




            }
        });
        //b_clear.setOnClickListener(this);






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
                loading = ProgressDialog.show(FilterOption.this,"Loading","Plz Wait",true,true);
            }


            protected void onPostExecute(String res)
            {
                super.onPostExecute(res);
                if(loading.isShowing())
                    loading.dismiss();
                Log.d("After Checkbox:", res);
                Dialog d = new Dialog(FilterOption.this);
                d.setTitle("hacked");
                TextView tv = new TextView(FilterOption.this);
                tv.setText(res);

                d.setContentView(tv);
                d.show();
                //DisplayItems(res);
            }


        }
        viewtbl vw = new viewtbl();
        vw.execute(Config.URL_VIEW_PRODUCTS);

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




}
