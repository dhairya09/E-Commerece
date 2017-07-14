package com.example.dhairyapujara.beauty_hub;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


public class Add_User extends Activity implements View.OnClickListener {
    EditText e_user,e_pwd;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user);

        e_user = (EditText)findViewById(R.id.editTextUserName);
        e_pwd = (EditText)findViewById(R.id.editTextPassword);

        login = (Button)findViewById(R.id.buttonRegister);
        login.setOnClickListener(this);



    }


    @Override
    public void onClick(View view) {
        if(view == login)
        {
            adduser();

        }
    }

    private void adduser() {

         final String u_name = e_user.getText().toString();
        final String u_pwd = e_pwd.getText().toString();

        adduserindatabase(u_name,u_pwd);

    }

    private void adduserindatabase(final String u_name, final String u_pwd)
    {

        class Register extends AsyncTask<String,Void,String>
        {
            ProgressDialog dialog;
            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                dialog = ProgressDialog.show(Add_User.this,"Adding User",null,true,true);
            }

            @Override
            protected void onPostExecute(String res)
            {
                super.onPostExecute(res);
                dialog.dismiss();
                Dialog d = new Dialog(Add_User.this);
                TextView tv = new TextView(Add_User.this);
                tv.setText(res);
                d.setContentView(tv);
                d.setTitle("helpless");
                d.show();
                if(res.equalsIgnoreCase("successfully registered"))
                {
                    Intent i = new Intent(Add_User.this,Dashboard.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(Add_User.this, res, Toast.LENGTH_LONG).show();
                }


            }


            @Override
            protected String doInBackground(String... strings) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_EMP_UNAME,u_name);
                params.put(Config.KEY_EMP_PWD,u_pwd);

                RequestHandler rh = new RequestHandler();
                String result = rh.sendPostRequest(Config.URL_ADD_USER, params);
                return result;

            }
        }
        Register r = new Register();
        r.execute();

    }


}
