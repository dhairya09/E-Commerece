package com.example.dhairyapujara.beauty_hub;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;


public class Camera extends Activity implements View.OnClickListener {
    ImageView iv;
    ImageButton ib;
    Bitmap bmp;
    Button b;
    Intent i;
    final static int cameraData = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);
        initializer();
        ib.setOnClickListener(this);
        b.setOnClickListener(this);

        /*InputStream is = getResources().openRawResource(R.drawable.bh3);
        bmp = BitmapFactory.decodeStream(is);*/
    }
    private void initializer()
    {
        iv =(ImageView)findViewById(R.id.ReturnedPic);
        ib = (ImageButton)findViewById(R.id.TakePic);
        b = (Button)findViewById(R.id.SetWall);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_camera, menu);
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
        switch (view.getId())
        {
            case R.id.TakePic:
                i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,cameraData);
                                break;
            case R.id.SetWall:
                try {
                    getApplicationContext().setWallpaper(bmp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            bmp = (Bitmap)extras.get("data");
            iv.setImageBitmap(bmp);
        }
    }
}
