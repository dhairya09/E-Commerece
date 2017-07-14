package com.example.dhairyapujara.beauty_hub;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class SQLiteExample extends Activity implements View.OnClickListener {
    EditText eName,eHot;
    Button bupdate,bview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite);
        eName = (EditText)findViewById(R.id.SqlName);
        eHot = (EditText)findViewById(R.id.etHot);
        bupdate = (Button)findViewById(R.id.bupdate);
        bview  = (Button)findViewById(R.id.bview);
        bupdate.setOnClickListener(this);
        bview.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.bupdate:  boolean didItWork = true;
                                try{
                                String str = eName.getText().toString();
                                String str1 = eHot.getText().toString();
                                HotOrNot up = new HotOrNot(SQLiteExample.this);
                                up.open();

                                up.dataEntry(str,str1);
                                up.close();
                                }catch(Exception e){
                                    didItWork = false;
                                    Dialog d = new Dialog(this);
                                    d.setTitle("Fuck Yea");
                                    TextView tv = new TextView(this);
                                    tv.setText("Failed");
                                    d.setContentView(tv);
                                    d.show();
                                }finally {
                                    if(didItWork){
                                        Dialog d = new Dialog(this);
                                        d.setTitle("Hack Yea");
                                        TextView tv = new TextView(this);
                                        tv.setText("Success");
                                        d.setContentView(tv);
                                        d.show();
                                    }
                                }

                                break;
            case R.id.bview:
                                break;
        }
    }
}
