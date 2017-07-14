package com.example.dhairyapujara.beauty_hub;

import android.app.Application;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SearchBar extends ActionBarActivity implements TextWatcher {

    EditText search;
    private ListView lv;
    ArrayAdapter<String> adapter;
    ArrayList<String> brake = new ArrayList<>();
    ArrayList<String> filterd = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bar);

        String []products = getResources().getStringArray(R.array.Adobe_Products);

        getBrand();

        search = (EditText)findViewById(R.id.inputSearch);
        lv = (ListView)findViewById(R.id.list_view);
        adapter = new ArrayAdapter<String>(this,R.layout.list_item1,brake);
        lv.setAdapter(adapter);

        search.addTextChangedListener(this);


        lv.setItemsCanFocus(false);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        lv.setOnItemClickListener(new CheckBoxClick());





    }

    /*public class FinalData_Brand extends Application {
        private ArrayList<String> brands = new ArrayList<>();

        public ArrayList<String> getValue(){
            return brands;
        }
        public void setValue(ArrayList<String> val){
            brands = val;
            Log.d("Final vish brands:", String.valueOf(brands));
        }

    }*/

    public class CheckBoxClick implements AdapterView.OnItemClickListener
    {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            CheckedTextView ctv = (CheckedTextView)view;
            if(ctv.isChecked())
            {
                Toast.makeText(getApplicationContext(), "It's checked now", Toast.LENGTH_SHORT).show();
                String b_name = ctv.getText().toString();
                filterd.add(b_name);
                Log.d("Checked", String.valueOf(filterd));
            }
            else
            {
                Toast.makeText(getApplicationContext(),"It's unchecked now",Toast.LENGTH_SHORT).show();
                String b_name = ctv.getText().toString();
                filterd.remove(b_name);
                Log.d("Unchecked",String.valueOf(filterd));

            }
            Log.d("Final Brand Filter", String.valueOf(filterd));


            Choices.setBrands(filterd);




        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_bar, menu);
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
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            SearchBar.this.adapter.getFilter().filter(charSequence);
    }

    @Override
    public void afterTextChanged(Editable editable) {

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
                Log.d("Brands", s);
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

    private void showBrand(String s) {
        JSONArray jarr = null;

        JSONObject json = null;
        try {
            json = new JSONObject(s);
            jarr = json.getJSONArray(Config.TAG_JSON_ARRAY);
            Log.d("Brand_ayo",s);
            for (int i = 0; i < jarr.length(); i++) {
                JSONObject c = jarr.getJSONObject(i);
                String name = c.getString(Config.TAG_NAME);
                brake.add(name);
                Log.d("ArrayList", String.valueOf(brake));
            }
            Log.d("ArrayList1", String.valueOf(brake));

        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

}
