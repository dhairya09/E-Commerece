package com.example.dhairyapujara.beauty_hub;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;


public class View_Products extends Activity implements View.OnClickListener {

    Button products;
    LinearLayout l1,l2;
    final CheckBox[] checkboxbuttons = new CheckBox[50];
    private static final String TAG_ID = "id";

    int id = 1;

    //ArrayList<String> selchkboxGender;
    //ArrayList<HashMap<String, String>> selchkboxGender;
    ArrayList<String> selchkboxGender;
    JSONObject JSONGender = new JSONObject();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products);


        selchkboxGender = new ArrayList<String>();
        //selchkboxGender = new ArrayList<HashMap<String, String>>();
        products = new Button(View_Products.this);
        products.setText("View Products");

        l1 = (LinearLayout)findViewById(R.id.vish);
        l2 = (LinearLayout)findViewById(R.id.babe);

        l1.addView(products);
        products.setOnClickListener(this);

        getGender();



    }

    private void getGender()
    {
        class Gender extends AsyncTask<String,Void,String>
        {
            ProgressDialog loading;
            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                loading = ProgressDialog.show(View_Products.this,"Loading","Plz Wait",true,true);

            }
            @Override
            protected String doInBackground(String... params) {
                String url = params[0];
                RequestHandler rh =  new RequestHandler();
                String allgen = rh.sendGetRequest(url);
                return allgen;
            }
            protected void onPostExecute(String res)
            {
                super.onPostExecute(res);
                Log.d("Gender", res);
                Dialog d = new Dialog(View_Products.this);
                TextView tv = new TextView(View_Products.this);
                tv.setText(res);
                d.setTitle("hacked");
                d.setContentView(tv);
                d.show();

                showGender(res);

            }
        }
        Gender g = new Gender();
        g.execute(Config.URL_GET_ALL_GEN);
    }

    private void showGender(String gen)
    {
        JSONArray jarr =null;
        JSONObject json = null;
        try {
            json = new JSONObject(gen);
            jarr = json.getJSONArray(Config.TAG_JSON_ARRAY);
            for(int i=0;i<jarr.length();i++)
            {
                JSONObject c = jarr.getJSONObject(i);
                String name = c.getString(Config.TAG_GENDER_NAME);
                createCB(i,name);



            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void createCB(int i,String name)
    {
        checkboxbuttons[i] = new CheckBox(View_Products.this);
        checkboxbuttons[i].setText(name);
        checkboxbuttons[i].setId(i + 3);
        checkboxbuttons[i].setTextColor(Color.RED);
        l2.addView(checkboxbuttons[i]);

        String chk = Integer.toString(checkboxbuttons[i].getId());

        // tmp hashmap for single contact
        //HashMap<String, String> contact = new HashMap<String, String>();
        selchkboxGender.add(chk);
        // adding each child node to HashMap key => value
        //contact.put(TAG_ID, chk);


        // adding contact to contact list
        //selchkboxGender.add(contact);
        /*checkboxbuttons[i].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                if (((CheckBox) v).isChecked()) {
                    String chk = Integer.toString(v.getId());

                    selchkboxGender.add(chk);
                    Toast.makeText(View_Products.this, "Selected CheckBox ID" + v.getId(), Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view__products, menu);
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


    private void viewTable(JSONObject jsonGender)
    {
        class vtbl extends AsyncTask<Object,Object,String>
        {
            ProgressDialog loading;
            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                loading = ProgressDialog.show(View_Products.this,"Loading","Wait",true,true);
            }

            @Override
            protected String doInBackground(Object... params)
            {
                String result = null;
                ArrayList<NameValuePair> postvars = new ArrayList<NameValuePair>();
                postvars.add(new BasicNameValuePair("JSON",String.valueOf(params)));
                ServiceHandler sh = new ServiceHandler();
                result = sh.makeServiceCall(Config.URL_VIEW_PRODUCTS,2,postvars);
                return result;

                    /*Log.d("Gender_Id", String.valueOf(params));
                    String objs = (String) params[0];
                    HashMap<String, String> emp = new HashMap<>();
                    emp.put("JSON", objs);
                    RequestHandler rh = new RequestHandler();
                    String yo =  rh.sendPostRequest(Config.URL_VIEW_PRODUCTS,emp);

                    return yo;*/
            }
            protected void onPostExecute(String res)
            {
                super.onPostExecute(res);
                if (loading.isShowing())
                    loading.dismiss();

                Log.d("Ids",res);
                Dialog d = new Dialog(View_Products.this);
                TextView tv = new TextView(View_Products.this);
                tv.setText(res);
                d.setTitle("hacked");
                d.setContentView(tv);
                d.show();

            }
        }
        vtbl obj = new vtbl();
        obj.execute(jsonGender);
    }

    private void putinJSON(JSONObject jsonGender, ArrayList<String> selchkboxGender)
    {
        for (int j = 0; j < selchkboxGender.size(); j++) {

            try {

                jsonGender.put("Gender"+String.valueOf((j + 1)),selchkboxGender.get(j));


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onClick(View view) {

        Log.d("Only Ids", String.valueOf(selchkboxGender));
        putinJSON(JSONGender, selchkboxGender);

        Log.d("Gender_Id", String.valueOf(JSONGender));

        viewTable(JSONGender);
    }
}
