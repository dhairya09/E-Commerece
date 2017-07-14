package com.example.dhairyapujara.beauty_hub;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.example.dhairyapujara.beauty_hub.R.drawable.bh3;


public class ExternalData extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    TextView canRead,canWrite,tv;
    Button con,sa;

    EditText et;
    private String state =null;
    boolean canR,canW;
    Spinner s;
    File path = null;
    File file = null;
    String[] paths = {"downloads","music","Pictures"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.external);
        canRead = (TextView)findViewById(R.id.tv1);
        canWrite = (TextView)findViewById(R.id.tv2);
        tv = (TextView)findViewById(R.id.tvSaveAs);
        et = (EditText)findViewById(R.id.etSave);
        con = (Button)findViewById(R.id.bConfirm);
        sa = (Button)findViewById(R.id.bSave);
        con.setOnClickListener(this);
        sa.setOnClickListener(this);
        checkState();


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ExternalData.this,android.R.layout.simple_list_item_1,paths);
        s = (Spinner)findViewById(R.id.spinner1);
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(this);
    }

    private void checkState() {
        state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED))
        {
            //can read and write
            canRead.setText("true");
            canWrite.setText("true");
            canR = canW = true;

        }
        else if(state.equals(Environment.MEDIA_MOUNTED_READ_ONLY))
        {
            canRead.setText("true");
            canWrite.setText("false");
            canR = true;
            canW = false;

        }
        else{

            canRead.setText("false");
            canWrite.setText("false");
            canR = canW =false;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int pos = s.getSelectedItemPosition();
        switch (pos)
        {
            case 0: path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                    break;
            case 1: path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
                    break;
            case 2: path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                    break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.bSave:
                    String str = et.getText().toString();
                    file = new File(path,str);
                    checkState();

                    if(canR == canW == true )
                    {
                        path.mkdirs();
                        /*try {

                            InputStream is = getResources().openRawResource(drawable.bh3);
                            OutputStream os = new FileOutputStream(file);
                            byte[] data = new byte[is.available()];
                            is.read(data);
                            os.write(data);
                            is.close();
                            os.close();
                            Toast t = Toast.makeText(ExternalData.this,"Data has been Saved",Toast.LENGTH_LONG);
                            t.show();
                            MediaScannerConnection.scanFile(ExternalData.this, new String[]{file.toString()},
                                    null,
                                    new MediaScannerConnection.OnScanCompletedListener() {
                                        @Override
                                        public void onScanCompleted(String s, Uri uri) {
                                            Toast t = Toast.makeText(ExternalData.this,"Data Scanned",Toast.LENGTH_LONG);
                                        }
                                    });






                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }*/
                    }



                    break;
                case R.id.bConfirm:
                                    sa.setVisibility(View.VISIBLE);
                                    break;
            }
    }
}
