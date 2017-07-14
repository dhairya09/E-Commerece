package com.example.dhairyapujara.beauty_hub;

import android.app.Application;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class PriceFilter extends AppCompatActivity  {


    private ListView lv;
    ArrayAdapter<String> adapter;
    ArrayList<String> filterd_price = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_filter);


        String []Prices = getResources().getStringArray(R.array.Price);


        lv = (ListView)findViewById(R.id.list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item1,Prices);
        lv.setAdapter(adapter);
        lv.setItemsCanFocus(false);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        lv.setOnItemClickListener(new CheckBoxClick());

        Log.d("After price_filter", String.valueOf(filterd_price));
    }

    /*public class FinalData_Price extends Application {
        private ArrayList<String> prices = new ArrayList<>();

        public ArrayList<String> getValue(){
            return prices;
        }
        public void setValue(ArrayList<String> val){
            prices = val;
            Log.d("Final vish prices:", String.valueOf(prices));
        }

    }*/

    public class CheckBoxClick implements AdapterView.OnItemClickListener
    {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            CheckedTextView ctv = (CheckedTextView)view;
            if(ctv.isChecked())
            {
                Toast.makeText(getApplicationContext(),"It's checked now",Toast.LENGTH_SHORT).show();
                String p_name = ctv.getText().toString();
                filterd_price.add(p_name);
                Log.d("Checked", String.valueOf(filterd_price));
            }
            else
            {
                Toast.makeText(getApplicationContext(),"It's unchecked now",Toast.LENGTH_SHORT).show();
                String p_name = ctv.getText().toString();
                filterd_price.remove(p_name);
                Log.d("Unchecked",String.valueOf(filterd_price));

            }
            Choices.setPrices(filterd_price);
        }

    }
















    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_brand_filter, menu);
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
