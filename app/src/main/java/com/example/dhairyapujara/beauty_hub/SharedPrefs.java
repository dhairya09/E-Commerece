package com.example.dhairyapujara.beauty_hub;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class SharedPrefs extends Activity implements View.OnClickListener {
    EditText data;
    Button save,load;
    TextView tv;
    SharedPreferences someData;
    public static String filename = "myFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prefs);
        initializers();


        someData = getSharedPreferences(filename,0);

    }
    public void initializers()
    {
        data = (EditText)findViewById(R.id.eText);
        save = (Button)findViewById(R.id.bSave);
        load = (Button)findViewById(R.id.bLoad);
        tv = (TextView)findViewById(R.id.tvData);
        save.setOnClickListener(this);
        load.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bSave:
                            String str = data.getText().toString();
                            SharedPreferences.Editor editor = someData.edit();
                            editor.putString("sharedData",str);
                            editor.commit();
                            break;
            case R.id.bLoad:
                            someData = getSharedPreferences(filename,0);
                            String returnedData = someData.getString("sharedData","Couldn't load Data");
                            tv.setText(returnedData);
                            break;
        }
    }


}
