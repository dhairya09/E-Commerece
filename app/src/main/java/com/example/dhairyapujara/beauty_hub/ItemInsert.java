package com.example.dhairyapujara.beauty_hub;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;


public class ItemInsert extends Activity implements View.OnClickListener {
    private EditText etCName;
    private EditText etIName;
    private EditText etPos;
    private EditText etVis;

    private Button buttonAdd;
    private Button buttonView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items);
        etCName = (EditText)findViewById(R.id.editCategoryName);
        etIName = (EditText)findViewById(R.id.editItemName);
        etPos = (EditText)findViewById(R.id.editTextPos);
        etVis = (EditText)findViewById(R.id.editTextVis);

        buttonAdd = (Button)findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);

        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
    }
    private void addEmployee(){

        final String c_name = etCName.getText().toString().trim();
        final String name = etIName.getText().toString().trim();
        final String pos = etPos.getText().toString().trim();
        final String vis = etVis.getText().toString().trim();

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ItemInsert.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ItemInsert.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_EMP_CNAME,c_name);
                params.put(Config.KEY_EMP_NAME,name);
                params.put(Config.KEY_EMP_POS,pos);
                params.put(Config.KEY_EMP_VIS,vis);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD_ITEM, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd){
            addEmployee();
        }

        if(v == buttonView){
            startActivity(new Intent(this,ViewAllItems.class));

        }
    }
}
