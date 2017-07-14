package com.example.dhairyapujara.beauty_hub;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

/**
 * Created by Dhairya Pujara on 1/25/2016.
 */
public class MyBringBack extends View
{
    Bitmap gBall;
    float changingY;
    Typeface font;
    public MyBringBack(Context context) {
        super(context);

        gBall = BitmapFactory.decodeResource(getResources(),R.drawable.bh_splash);
        changingY = 0;
        //font = Typeface.createFromAsset(context.getAssets(),"");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint myText = new Paint();
        myText.setARGB(50,250,100,35);
        myText.setTextAlign(Paint.Align.CENTER);
        myText.setTextSize(50);
        //myText.setTypeface(font);
        canvas.drawText("dhairya",(canvas.getWidth()/2),200,myText);

        canvas.drawColor(Color.GRAY);
        canvas.drawBitmap(gBall,(canvas.getWidth()/2),changingY,null);


        if(changingY < canvas.getHeight())
        {
            changingY += 10;
        }
        else
        {
            changingY = 0;
        }
        Rect mr = new Rect();
        mr.set(0,400,canvas.getWidth(),550);
        Paint col = new Paint();
        col.setColor(Color.BLUE);
        canvas.drawRect(mr, col);
        invalidate();

    }
}
