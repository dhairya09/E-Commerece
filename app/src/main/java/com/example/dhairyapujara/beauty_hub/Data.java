package com.example.dhairyapujara.beauty_hub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Dhairya Pujara on 1/24/2016.
 */
public class Data extends Activity implements View.OnClickListener
{
    EditText et;
    Button start,startResult;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get);
        initializers();

        start.setOnClickListener(this);
        startResult.setOnClickListener(this);
    }
    private void initializers()
    {
        et = (EditText)findViewById(R.id.etSend);
        start = (Button)findViewById(R.id.bSA);
        startResult = (Button)findViewById(R.id.bSAR);
        tv = (TextView)findViewById(R.id.results);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.bSA:
                            String bread = et.getText().toString();
                            Bundle basket = new Bundle();
                            basket.putString("key",bread);
                            Intent i  =new Intent(Data.this,OpenedClass.class);
                            i.putExtras(basket);
                            startActivity(i);

                            break;
            case R.id.bSAR:
                            Intent in =new Intent(Data.this,OpenedClass.class);
                            startActivityForResult(in,0);

                            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
        {
            Bundle basket =data.getExtras();
            String s = basket.getString("answer");
            tv.setText(s);

        }
    }
}
