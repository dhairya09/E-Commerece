package com.example.dhairyapujara.beauty_hub;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Dhairya Pujara on 1/24/2016.
 */
public class OpenedClass extends Activity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener {


    Button returnData;
    TextView Question,test;
    RadioGroup selectionList;
    String gotBread;
    String sd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send);
        initializers();

        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String et = getData.getString("name","Dhairya is");
        String values = getData.getString("list","4");
        if(values.contentEquals("1"))
        {
            Question.setText(et);
        }
        //Bundle gotbasket = getIntent().getExtras();
        //gotBread = gotbasket.getString("key");
        //Question.setText(gotBread);
        returnData.setOnClickListener(this);
        selectionList.setOnCheckedChangeListener(this);
    }
    private void initializers()
    {

        returnData = (Button)findViewById(R.id.Rdata);
        Question = (TextView)findViewById(R.id.tvQue);
        test = (TextView)findViewById(R.id.tvTest);
        selectionList = (RadioGroup)findViewById(R.id.rgAns);

    }

    @Override
    public void onClick(View view)
    {
        Intent i = new Intent();
        Bundle basket = new Bundle();
        basket.putString("answer",sd);
        i.putExtras(basket);
        setResult(RESULT_OK,i);
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i)
        {
            case R.id.rCrazy:
                                sd = "gani";
                                break;
            case R.id.rSexy:
                sd = "tari";
                break;
            case R.id.rBoth:
                sd = "mani";
                break;
        }
        test.setText(sd);
    }
}
