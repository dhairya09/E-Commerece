package com.example.dhairyapujara.beauty_hub;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class ViewImage extends AppCompatActivity implements View.OnClickListener {

    EditText e_id;
    Button b_get;
    ImageView iv_disp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_image);

        e_id = (EditText)findViewById(R.id.editTextId);
        b_get = (Button)findViewById(R.id.buttonGetImage);
        iv_disp = (ImageView)findViewById(R.id.imageViewShow);

        b_get.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_image, menu);
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
        getImage();
    }

    private void getImage()
    {
        String img_id = e_id.getText().toString();
        getImageFromDb(img_id);

    }

    private void getImageFromDb(String img_id)
    {

        class FetchImage extends AsyncTask<String,Void,Bitmap>
        {
            ProgressDialog loading;
            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewImage.this,"Loading",null,true,true);

            }
            @Override
            protected void onPostExecute(Bitmap b)
            {
                super.onPostExecute(b);
                loading.dismiss();

                iv_disp.setImageBitmap(b);

            }
            @Override
            protected Bitmap doInBackground(String... params)
            {

                String id = params[0];
                String add = "http://192.168.43.157/image_upload/IMG_fetch_app.php?id="+id;

                URL url = null;
                Bitmap image = null;
                try {
                    url = new URL(add);
                    image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return image;



            }
        }
        FetchImage fi = new FetchImage();
        fi.execute(img_id);

    }
}
