package com.example.dhairyapujara.beauty_hub;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;


public class TextPlay extends Activity implements View.OnClickListener{
    Button btn;
    ToggleButton tbtn;
    TextView tv;
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_play);
        fun();
        btn.setOnClickListener(this);
        tbtn.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_text_play, menu);
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
    private void fun()
    {
        et = (EditText)findViewById(R.id.trycmd);
        btn = (Button)findViewById(R.id.btn);
        tbtn = (ToggleButton)findViewById(R.id.tgbtn);
        tv = (TextView)findViewById(R.id.results);

    }
    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.btn:String str = et.getText().toString();
                tv.setText(str);
                if(str.contentEquals("left"))
                {
                    tv.setGravity(Gravity.LEFT);
                }
                else if(str.contentEquals("right"))
                {
                    tv.setGravity(Gravity.RIGHT);
                }
                else if(str.contentEquals("center"))
                {
                    tv.setGravity(Gravity.CENTER);
                }
                else if(str.contentEquals("blue"))
                {
                    tv.setTextColor(Color.BLUE);
                }
                else if(str.contains("WTF"))
                {
                    Random r = new Random();
                    tv.setGravity(Gravity.CENTER);
                    tv.setTextSize(r.nextInt(75));
                    tv.setTextColor(Color.rgb(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
                    switch (r.nextInt(3))
                    {
                        case 0: tv.setGravity(Gravity.LEFT);
                            break;
                        case 1: tv.setGravity(Gravity.CENTER);
                            break;
                        case 2: tv.setGravity(Gravity.RIGHT);
                            break;
                    }
                }
                else
                {
                    tv.setText("invalid");
                    tv.setGravity(Gravity.CENTER);
                }




                break;

            case R.id.tgbtn:
                if(tbtn.isChecked()){
                et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
            else{
                et.setInputType(InputType.TYPE_CLASS_TEXT);
            }







                break;

        }
    }
}
