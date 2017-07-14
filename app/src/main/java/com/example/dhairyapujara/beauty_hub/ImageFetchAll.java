package com.example.dhairyapujara.beauty_hub;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.drm.ProcessedData;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class ImageFetchAll extends AppCompatActivity implements View.OnClickListener {
    Button b_fetch,b_next,b_prev;
    ImageView iv;
    private static final String IMAGE_FETCH_URL = "http://192.168.0.104/image_upload/IMG_fetchAll_app.php";
    private static final String JSON_ARRAY = "result";
    private static final String IMAGE_URL = "url";
    private String imagesJSON = null;
    private JSONArray arrayImages = null;
    private int TRACK = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_fetch_all);



        b_fetch = (Button)findViewById(R.id.buttonFetchImages);
        b_next = (Button)findViewById(R.id.buttonNext);
        b_prev = (Button)findViewById(R.id.buttonPrev);
        iv = (ImageView)findViewById(R.id.imageView);

        b_fetch.setOnClickListener(this);
        b_next.setOnClickListener(this);
        b_prev.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == b_fetch)
        {
            getAllImages();
        }
        if(v == b_next)
        {
            moveNext();
        }
        if(v == b_prev)
        {
            movePrev();
        }
    }

    private void getAllImages()
    {

        class GetAllImages extends AsyncTask<String,Void,String>
        {
            ProgressDialog loading;
            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                loading = ProgressDialog.show(ImageFetchAll.this,"Fetching Images","Please Wait",true,true);

            }
            @Override
            protected void onPostExecute(String str)
            {
                super.onPostExecute(str);
                loading.dismiss();
                /*Dialog d = new Dialog(ImageFetchAll.this);
                d.setTitle("hacked");
                TextView tv = new TextView(ImageFetchAll.this);
                tv.setText(str);
                d.setContentView(tv);
                d.show();*/
                imagesJSON = str;
                extractJSON();
                showImage();
            }
            @Override
            protected String doInBackground(String... strings) {
                String url = strings[0];
                RequestHandler rh = new RequestHandler();
                String str = rh.sendGetRequest(url);
                return str;


            }
        }
        GetAllImages g = new GetAllImages();
        g.execute(IMAGE_FETCH_URL);
    }

    private void showImage()
    {
        JSONObject js = null;
        try {
            js = arrayImages.getJSONObject(TRACK);
            getImage(js.getString(IMAGE_URL));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void getImage(String string)
    {
        class GetImage extends AsyncTask<String,Void,Bitmap>
        {
            ProgressDialog load;
            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                load = ProgressDialog.show(ImageFetchAll.this,"Downloading Image","Plz Wait",true,true);

            }

            @Override
            protected void onPostExecute(Bitmap bitmap)
            {
                super.onPostExecute(bitmap);
                load.dismiss();
                iv.setImageBitmap(bitmap);
            }

            @Override
            protected Bitmap doInBackground(String... strings) {
                Bitmap b = null;
                URL url = null;
                String url_p_image = strings[0];

                try {
                    url = new URL(url_p_image);
                    b = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                return b;
            }
        }
        GetImage gi = new GetImage();
        gi.execute(string);

    }

    private void extractJSON()
    {
        try {
            JSONObject jsonObj = new JSONObject(imagesJSON);
            arrayImages = jsonObj.getJSONArray(JSON_ARRAY);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void movePrev() {
            if(TRACK > 0)
            {
                TRACK--;
                showImage();
            }
    }

    private void moveNext() {
            if(TRACK < arrayImages.length())
            {
                TRACK++;
                showImage();
            }
    }
}