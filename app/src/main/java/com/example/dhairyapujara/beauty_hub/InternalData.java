package com.example.dhairyapujara.beauty_hub;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class InternalData extends Activity implements View.OnClickListener {
    EditText data;
    Button save,load;
    TextView tv;
    FileOutputStream fos;
    public static String filename = "MyFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prefs);
        initializers();
    }
    public void initializers()
    {
        data = (EditText)findViewById(R.id.eText);
        save = (Button)findViewById(R.id.bSave);
        load = (Button)findViewById(R.id.bLoad);
        tv = (TextView)findViewById(R.id.tvData);
        save.setOnClickListener(this);
        load.setOnClickListener(this);
        try {
            fos = openFileOutput(filename,MODE_PRIVATE);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {

            case R.id.bSave:
                /*File f = new File(filename);
                try {
                    fos = new FileOutputStream(f);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }*/
                String data1 = data.getText().toString();
                try {
                    fos = openFileOutput(filename,MODE_PRIVATE);
                    fos.write(data1.getBytes());
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                break;
            case R.id.bLoad:
                            new loadSomeStuff().execute(filename);

                break;
        }
    }
    public class loadSomeStuff extends AsyncTask<String,Integer,String>
    {
        ProgressDialog dialog;
        protected void onPreExecute()
        {
            dialog = new ProgressDialog(InternalData.this);
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setMax(100);
            dialog.show();


        }

        @Override
        protected String doInBackground(String... strings) {
            FileInputStream fis =null;
            for(int i=0;i<20;i++)
            {
                publishProgress(5);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            dialog.dismiss();
            String collected = null;
            try {
                fis = openFileInput(filename);
                byte[] dataArr = new byte[fis.available()];
                while(fis.read(dataArr)!= -1)
                {
                    collected = new String (dataArr);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    fis.close();
                    return collected;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        protected void onProgressUpdate(Integer...progress)
        {
                dialog.incrementProgressBy(progress[0]);
        }
        protected void onPostExecute(String res)
        {
            tv.setText(res);
        }
    }

}
