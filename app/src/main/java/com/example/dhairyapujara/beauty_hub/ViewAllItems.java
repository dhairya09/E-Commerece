package com.example.dhairyapujara.beauty_hub;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;




public class ViewAllItems extends ActionBarActivity implements ListView.OnItemClickListener {

    private ListView listView;

    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_items);
        listView = (ListView) findViewById(R.id.listView3);
        listView.setOnItemClickListener(this);
        getJSON();
    }


    private void showEmployee() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray people = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for (int i = 0; i < people.length(); i++) {
                JSONObject jo = people.getJSONObject(i);
                String id = jo.getString(Config.TAG_ID);
                String name = jo.getString(Config.TAG_NAME);

                HashMap<String, String> employees = new HashMap<>();
                employees.put(Config.TAG_ID, id);
                employees.put(Config.TAG_NAME, name);
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                ViewAllItems.this, list, R.layout.list_item,
                new String[]{Config.TAG_ID, Config.TAG_NAME},
                new int[]{R.id.id, R.id.name});

        listView.setAdapter(adapter);
        //setListAdapter(new ArrayAdapter<String>(ViewAllBrands.this, android.R.layout.simple_list_item_1, list));
    }

    private void getJSON() {
        class GetJSON extends AsyncTask<String, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                loading = ProgressDialog.show(ViewAllItems.this, "Fetching Data", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                loading.dismiss();
                JSON_STRING = result;
                /*Dialog d = new Dialog(ViewAllBrands.this);
                TextView tv = new TextView(ViewAllBrands.this);
                tv.setText(JSON_STRING);
                d.setContentView(tv);
                d.setTitle("helpless");
                d.show();*/
                showEmployee();
            }

            @Override
            protected String doInBackground(String... params) {


                RequestHandler rh = new RequestHandler();
                String res = rh.sendGetRequest(Config.URL_GET_ALL_ITEM);
                return res;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ViewItem.class);
        HashMap<String, String> map = (HashMap) parent.getItemAtPosition(position);
        String empId = map.get(Config.TAG_ID).toString();
        intent.putExtra(Config.EMP_ID, empId);
        startActivity(intent);
    }


}