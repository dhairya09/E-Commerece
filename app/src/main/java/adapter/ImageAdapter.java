package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.dhairyapujara.beauty_hub.R;

/**
 * Created by Dhairya Pujara on 2/28/2016.
 */
public class ImageAdapter extends BaseAdapter
{
    private Context mContext;

    public Integer[] mThumbIds = {
            R.drawable.bh2_splash, R.drawable.bh3,
            R.drawable.bh_splash, R.drawable.ic_drawer,
            R.drawable.ic_communities,R.drawable.ic_home

    };
    public ImageAdapter(Context c)
    {
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(mContext);
        //imageView.setImageBitmap(mThumbIds[position]);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(70, 70));
        return imageView;

    }
}
