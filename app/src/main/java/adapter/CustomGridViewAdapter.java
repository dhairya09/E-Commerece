package adapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dhairyapujara.beauty_hub.DisplayItem;
import com.example.dhairyapujara.beauty_hub.FullImage;
import com.example.dhairyapujara.beauty_hub.FullImageActivity;
import com.example.dhairyapujara.beauty_hub.R;
import com.example.dhairyapujara.beauty_hub.RequestHandler;
import com.squareup.picasso.Picasso;

public class CustomGridViewAdapter extends BaseAdapter {
    Context context;
    //private static LayoutInflater inflater=null;

    ArrayList<String> desc_arr = new ArrayList<String>();
    ArrayList<String> price_arr = new ArrayList<String>();
    ArrayList<String> path_arr = new ArrayList<String>();

    //ArrayList<Item> data = new ArrayList<Item>();
    public CustomGridViewAdapter(Context context, ArrayList<String> desc_arr,ArrayList<String> price_arr,ArrayList<String> path_arr)
    {
        this.context = context;

        this.desc_arr = desc_arr;
        this.price_arr = price_arr;
        this.path_arr = path_arr;

        Log.d("Desc_dp1", String.valueOf(desc_arr));
        Log.d("Price_dp1", String.valueOf(price_arr));
        Log.d("Path_dp1", String.valueOf(path_arr));

        



    }


    private Bitmap DownloadImage(String URL)
    {
        Bitmap bitmap = null;
        //InputStream in = null;
        RequestHandler rh = new RequestHandler();

        /*try {
            in = OpenHttpConnection(URL);
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        bitmap = rh.sendRequestImg(URL);

        return bitmap;
    }
    private InputStream OpenHttpConnection(String urlString)
            throws IOException
    {
        InputStream in = null;
        int response = -1;

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");

        try{
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            response = httpConn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        }
        catch (Exception ex)
        {
            throw new IOException("Error connecting");
        }
        return in;
    }






    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return desc_arr.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        ImageView iv_image;
        TextView tv_price;
        TextView tv_desc;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView = convertView;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        rowView = inflater.inflate(R.layout.row_grid, parent, false);

        //Bitmap bitmap = DownloadImage(path_arr.get(position));
        //ImageView img = (ImageView) findViewById(R.id.img);
        //img.setImageBitmap(bitmap);


        //rowView = inflater.inflate(R.layout.row_grid, null);
        holder.tv_price=(TextView) rowView.findViewById(R.id.price);
        holder.tv_desc=(TextView) rowView.findViewById(R.id.desc);
        //holder.iv_image = (ImageView) rowView.findViewById(R.id.grid_item_image);

         new DisplayImageFromURL((ImageView) rowView.findViewById(R.id.grid_item_image))
                .execute(path_arr.get(position));



        //holder.iv_image.setImageBitmap(bitmap);
        holder.tv_price.setText(price_arr.get(position));
        holder.tv_desc.setText(desc_arr.get(position));

        //Picasso.with(context).load(String.valueOf(DownloadImage(path_arr.get(position)))).into(holder.iv_image);

        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String s = ""+desc_arr.get(position)+" "+price_arr.get(position)+" "+path_arr.get(position);
                Log.d("heli",s);
                Toast.makeText(context, "You Clicked " + price_arr.get(position), Toast.LENGTH_LONG).show();
                Intent i = new Intent(context,FullImage.class);
                i.putExtra("id",s);
                context.startActivity(i);
            }
        });

        return rowView;
    }

    class DisplayImageFromURL extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pd = new ProgressDialog(context);
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


}




