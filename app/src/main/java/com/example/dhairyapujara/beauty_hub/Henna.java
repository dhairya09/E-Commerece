package com.example.dhairyapujara.beauty_hub;

import android.app.ProgressDialog;
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


public class Henna extends ActionBarActivity implements View.OnClickListener {

    EditText e1,e2,e3,e4;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_henna);
        e1 = (EditText)findViewById(R.id.d1);
        e2 = (EditText)findViewById(R.id.d2);
        e3 = (EditText)findViewById(R.id.em1);
        e4 = (EditText)findViewById(R.id.em2);
        b1 = (Button)findViewById(R.id.call);
        b1.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_henna, menu);
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
        final String doc1 = e1.getText().toString();
        final String doc2 = e2.getText().toString();
        final String eme1 = e3.getText().toString();
        final String eme2 = e4.getText().toString();
            class Call extends AsyncTask<String,Void,String>
            {
                ProgressDialog loading;
                @Override
                protected void onPreExecute()
                {
                    super.onPreExecute();
                    loading = ProgressDialog.show(getApplicationContext(),"Loading","Wait",true,true);

                }

                @Override
                protected String doInBackground(String... strings) {
                    String url = strings[0];
                    HashMap<String,String> params = new HashMap<String,String>();
                    params.put("Doc1",doc1);
                    params.put("Doc2",doc2);
                    params.put("Eme1",eme1);
                    params.put("Eme2",eme2);
                    RequestHandler rh = new RequestHandler();
                    String res = rh.sendPostRequest(url, params);
                    return res;
                }

                @Override
                protected void onPostExecute(String res)
                {
                    super.onPostExecute(res);
                    if(loading.isShowing())
                        loading.dismiss();

                    Toast.makeText(getApplicationContext(),
                            "Success",
                            Toast.LENGTH_SHORT).show();

                }

            }

        Call c = new Call();
        c.execute(Config.URL_ADD_HENNA);
    }
}
