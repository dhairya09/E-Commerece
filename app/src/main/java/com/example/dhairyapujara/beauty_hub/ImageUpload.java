package com.example.dhairyapujara.beauty_hub;

import android.app.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;


public class ImageUpload extends Activity implements View.OnClickListener {

    Button choose,upload,bview;
    ImageView iv;
    EditText name;
    String im_name;

    private int PICK_IMAGE_REQUEST =1;
    private Uri filePath;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageupload);
        choose = (Button)findViewById(R.id.buttonChoose);
        upload = (Button)findViewById(R.id.buttonUpload);
        bview = (Button)findViewById(R.id.buttonViewImage);
        iv = (ImageView)findViewById(R.id.imageView);
        name = (EditText)findViewById(R.id.img_name);

        choose.setOnClickListener(this);
        upload.setOnClickListener(this);
        bview.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.buttonChoose:filechooser();
                                    break;
            case R.id.buttonUpload:uploadimage();
                                    break;
            case R.id.buttonViewImage:viewimage();
                                        break;
        }
    }

    private void viewimage()
    {
        Intent i  = new Intent(ImageUpload.this,ViewImage.class);
        startActivity(i);
    }

    private void uploadimage()
    {
        im_name = name.getText().toString();
        uploadimageindb(im_name);
    }

    private void uploadimageindb(final String im_name)
    {
        ProgressDialog load;
        class Img_Upload extends AsyncTask<Bitmap,Void,String>
        {
            ProgressDialog load;
            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                load = ProgressDialog.show(ImageUpload.this,"Uploading Image",null,true,true);

            }
            @Override
            protected void onPostExecute(String result)
            {
                super.onPostExecute(result);
                load.dismiss();
                Dialog d = new Dialog(ImageUpload.this);
                d.setTitle("hacked");
                TextView tv = new TextView(ImageUpload.this);
                tv.setText(result);
                d.setContentView(tv);
                d.show();
            }

            @Override
            protected String doInBackground(Bitmap... strings) {
                Bitmap bp = strings[0];
                String imgs = getStringImage(bp);
                HashMap<String,String> data = new HashMap<>();
                data.put(Config.KEY_EMP_NAME,im_name);
                data.put(Config.KEY_IMAGE,imgs);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.KEY_IMG_URL, data);
                return res;
            }
        }
        Img_Upload iu = new Img_Upload();
        iu.execute(bitmap);
    }

    private void filechooser()
    {
       Intent i = new Intent();
        i.setType("image/*");
        i.setAction(i.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"), PICK_IMAGE_REQUEST);



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                iv.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
}
