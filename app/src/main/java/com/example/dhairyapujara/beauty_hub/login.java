package com.example.dhairyapujara.beauty_hub;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


public class login extends AppCompatActivity implements View.OnClickListener {
    public static  final String USER_NAME="USERNAME";
    EditText e_user,e_pwd;
    Button login;
    boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        e_user = (EditText)findViewById(R.id.editTextUserName);
        e_pwd = (EditText)findViewById(R.id.editTextPassword);

        login = (Button)findViewById(R.id.buttonLogin);
        login.setOnClickListener(this);
        if(loggedIn)
        {
            Intent in = new Intent(login.this,Menu.class);
            startActivity(in);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
        if(view == login)
        {
            check_user();
        }
    }

    private void check_user()
    {
        final String u_name= e_user.getText().toString();
        final String u_pwd = e_pwd.getText().toString();
        check_user_in_db(u_name,u_pwd);
    }

    private void check_user_in_db(final String u_name, final String u_pwd)
    {
        class Login_User extends AsyncTask<String,Void,String>
        {
            ProgressDialog loading;
            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                loading = ProgressDialog.show(login.this,"Checking",null,true,true);

            }
            protected void onPostExecute(String res)
            {
                super.onPostExecute(res);
                loading.dismiss();
                Dialog d = new Dialog(login.this);
                d.setTitle("Helpless");
                TextView tv = new TextView(login.this);
                tv.setText(res);

                d.setContentView(tv);
                d.show();
                d.dismiss();
                if(res.equals("Success"))
                {
                    Intent im = new Intent(login.this,Dashboard.class);
                    startActivity(im);
                }
                else
                {
                    Toast.makeText(login.this,"Invalid username and password",Toast.LENGTH_LONG);
                }
            }
            @Override
            protected String doInBackground(String... strings) {

                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_EMP_UNAME,u_name);
                params.put(Config.KEY_EMP_PWD,u_pwd);

                RequestHandler rh = new RequestHandler();
                String result = rh.sendPostRequest(Config.URL_USER_LOGIN, params);
                return result;


            }
        }
        Login_User lu = new Login_User();
        lu.execute();
    }
}
