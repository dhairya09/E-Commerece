package com.example.dhairyapujara.beauty_hub;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

import adapter.ImageAdapter;


public class FullImage extends ActionBarActivity {

    String res10;
    TextView tv_desc,tv_price;
    ImageView iv;
    String desc,price,img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image2);

        tv_desc = (TextView)findViewById(R.id.desc);
        tv_price = (TextView)findViewById(R.id.price);
        iv = (ImageView)findViewById(R.id.item_img);

        Bundle b = getIntent().getExtras();
        res10 = b.getString("id");
        Log.d("Vivek", res10);

        String p[] = res10.split(" ");
         desc = p[0];
         price = p[1];
         img = p[2];

        //DisplayItems(res2);

       //ImageAdapter imageAdapter = new ImageAdapter(this);

        tv_desc.setText(desc);
        tv_price.setText(price);
        new DisplayImageFromURL((ImageView) findViewById(R.id.item_img)).execute(img);

    }

    class DisplayImageFromURL extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pd = new ProgressDialog(getApplicationContext());
            pd.setMessage("Loading...");
            pd.show();
        }
        public DisplayImageFromURL(ImageView bmImage) {
            this.bmImage = bmImage;
        }
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            return mIcon11;

        }
        protected void onPostExecute(Bitmap result) {
            if(pd.isShowing())
                pd.dismiss();

            bmImage.setImageBitmap(result);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_full_image, menu);
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
}
