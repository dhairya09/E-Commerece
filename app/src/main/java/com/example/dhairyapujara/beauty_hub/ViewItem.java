package com.example.dhairyapujara.beauty_hub;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ViewItem extends AppCompatActivity implements View.OnClickListener {

    private EditText etId;
    private EditText etName;
    private EditText etPos;
    private EditText etVis;

    private Button buttonUpdate;
    private Button buttonDelete;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        Intent intent = getIntent();

        id = intent.getStringExtra(Config.EMP_ID);

        etId = (EditText) findViewById(R.id.editTextId);
        etName = (EditText) findViewById(R.id.editTextName);
        etPos = (EditText) findViewById(R.id.editTextPos);
        etVis = (EditText) findViewById(R.id.editTextVis);

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        etId.setText(id);

        getEmployee();
    }

    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewItem.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> param = new HashMap<>();
                param.put(Config.KEY_EMP_ID,id);
                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.URL_GET_EMP_ITEM,param);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String name = c.getString(Config.TAG_NAME);
            String pos = c.getString(Config.TAG_POS);
            String vis = c.getString(Config.TAG_VIS);

            etName.setText(name);
            etPos.setText(pos);
            etVis.setText(vis);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateItem(){
        final String id1 = etId.getText().toString().trim();
        final String name = etName.getText().toString().trim();
        final String pos = etPos.getText().toString().trim();
        final String vis = etVis.getText().toString().trim();

        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewItem.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if(s.equalsIgnoreCase("Brand Updated"))
                {
                    Dialog d = new Dialog(ViewItem.this);
                    d.setTitle("hacked");
                    TextView tv = new TextView(ViewItem.this);

                    tv.setText(s);
                    d.setContentView(tv);
                    startActivity(new Intent(ViewItem.this,ViewAllItems.class));
                }
                else
                {
                    Toast.makeText(ViewItem.this, s, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Config.KEY_EMP_ID,id1);
                hashMap.put(Config.KEY_EMP_NAME,name);
                hashMap.put(Config.KEY_EMP_POS,pos);
                hashMap.put(Config.KEY_EMP_VIS,vis);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Config.URL_UPDATE_ITEM,hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }

    private void deleteItem(){
        final String id2 = etId.getText().toString().trim();
        class DeleteEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewItem.this, "Deleting...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if(s.equalsIgnoreCase("Item Deleted"))
                {
                    Dialog d = new Dialog(ViewItem.this);
                    d.setTitle("hacked");
                    TextView tv = new TextView(ViewItem.this);

                    tv.setText(s);
                    d.setContentView(tv);
                    startActivity(new Intent(ViewItem.this,ViewAllItems.class));
                }
                else
                {
                    Toast.makeText(ViewItem.this, s, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                HashMap<String,String> hasj = new HashMap<>();
                hasj.put(Config.KEY_EMP_ID,id2);
                String s = rh.sendPostRequest(Config.URL_DELETE_ITEM, hasj);
                return s;
            }
        }

        DeleteEmployee de = new DeleteEmployee();
        de.execute();
    }

    private void confirmDeleteItem(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to delete this employee?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteItem();
                        startActivity(new Intent(ViewItem.this,ViewAllItems.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){
            updateItem();
        }

        if(v == buttonDelete){
            confirmDeleteItem();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_all_brands, menu);
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
