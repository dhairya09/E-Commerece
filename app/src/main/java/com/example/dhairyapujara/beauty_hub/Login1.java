package com.example.dhairyapujara.beauty_hub;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Login1 extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @InjectView(R.id.input_email) EditText _username;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_login) Button _loginButton;
    @InjectView(R.id.link_signup) TextView _signupLink;

    AlertDialogManager alert = new AlertDialogManager();
    SessionManager session;

    ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login1);
        ButterKnife.inject(this);

        session = new SessionManager(getApplicationContext());

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Login1.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _username.getText().toString();
        String password = _passwordText.getText().toString();
        check_user_in_db(email, password);


        // TODO: Implement your own authentication logic here.

        /*new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);*/
    }

    private void check_user_in_db(final String u_name, final String u_pwd)
    {
        class Login_User extends AsyncTask<String,Void,String>
        {

            protected void onPostExecute(String res)
            {
                super.onPostExecute(res);

                Log.d("Login Successfull",res);
                /*Dialog d = new Dialog(Login1.this);
                d.setTitle("Helpless");
                TextView tv = new TextView(Login1.this);
                tv.setText(res);

                d.setContentView(tv);
                d.show();
                d.dismiss();*/
                if(res.equals("Success"))
                {
                    Toast.makeText(Login1.this, "Successfull Login", Toast.LENGTH_LONG).show();
                    session.createSessionLogin(u_name, u_pwd);

                    // Staring MainActivity
                    Intent i = new Intent(getApplicationContext(), Welcome.class);
                    startActivity(i);
                    finish();

                }
                else
                {
                    Toast.makeText(Login1.this,"Invalid username and password",Toast.LENGTH_LONG).show();
                    alert.showAlertDialog(Login1.this, "Login failed..", "Username/Password is incorrect", false);

                }
            }
            @Override
            protected String doInBackground(String... strings) {
                String url = strings[0];
                Log.d("Username",u_name);
                Log.d("Password",u_pwd);
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_EMP_UNAME,u_name);
                params.put(Config.KEY_EMP_PWD,u_pwd);

                RequestHandler rh = new RequestHandler();
                String result = rh.sendPostRequest(url,params);
                Log.d("Result",result);
                return result;


            }
        }
        Login_User lu = new Login_User();
        lu.execute(Config.URL_USER_LOGIN);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _username.getText().toString();
        String password = _passwordText.getText().toString();

        //if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        /*if(u_name.isEmpty())
        {
            _username.setError("enter a valid User Name");
            valid = false;
        } else {
            _username.setError(null);
        }

        //if (password.isEmpty() || password.length() < 4 || password.length() > 10)
        if(password.isEmpty() || password.length() < 4 || password.length() > 10 )
        {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }*/

        if (!email.isEmpty())
        {
            if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                _username.setError("Enter a valid Email-address");
            }
            else{
                _username.setError(null);
            }

            valid = false;
        }
        else
        {
            _username.setError("Email can't be left blank");
            valid = false;
        }

        //if (password.isEmpty() || password.length() < 4 || password.length() > 10)
        if (!password.isEmpty()){
            if(password.length() <= 6){
                _passwordText.setError("Password must be greater than 6 characters");
            }
            else{
                _passwordText.setError(null);
            }

        }
        else
        {
            _passwordText.setError("Password can't be left blank");
            valid = false;
        }

        return valid;
    }
}