package com.example.dhairyapujara.beauty_hub;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;


public class GFXSurface extends Activity implements View.OnTouchListener {
    MyBringBackSurface ourview;
    float x,y,sX,sY,fX,fY,dX,dY,scaledX,scaledY,animX,animY;
    Bitmap b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ourview = new MyBringBackSurface(this);
        setContentView(ourview);
        ourview.setOnTouchListener(this);
        x=0;
        y=0;
        sX=0;
        sY=0;
        fX=0;
        fY=0;
        dX = dY = scaledY = scaledX = animX = animY = 0;
        b1 = BitmapFactory.decodeResource(getResources(),R.drawable.bh_splash);
        b2 = BitmapFactory.decodeResource(getResources(),R.drawable.bh2_splash);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ourview.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ourview.resume();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        x =  motionEvent.getX();
        y  = motionEvent.getY();
        switch (motionEvent.getAction())
        {
            case MotionEvent.ACTION_DOWN:   sX = motionEvent.getX();
                                            sY = motionEvent.getY();
                                            dX = dY = scaledY = scaledX = animX = animY = fX = fY = 0;
                                            break;
            case MotionEvent.ACTION_UP:
                                            fX = motionEvent.getX();
                fY = motionEvent.getY();
                                            dX = fX - sX;
                                            dY = fY - sY;
                                            scaledX = dX/30;
                                            scaledY = dY/30;
                                            x = y = 0;
                                            break;
        }
        return true;
    }

    class MyBringBackSurface extends SurfaceView implements  Runnable
    {
        SurfaceHolder ourHolder;
        Thread ourThread = null;
        boolean isRunning =true;

        public MyBringBackSurface(Context context) {
            super(context);
            ourHolder = getHolder();

        }

        public void pause()
        {
            isRunning = false;
            while (true)
            {
                try {
                    ourThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            ourThread = null;
        }
        public void resume()
        {
            isRunning = true;
            ourThread = new Thread(this);
            ourThread.start();
        }

        @Override
        public void run() {
            while(isRunning)
            {
                if(!ourHolder.getSurface().isValid())
                    continue;
                Canvas canvas = ourHolder.lockCanvas();
                canvas.drawRGB(02, 02, 125);
                if(x!=0 && y!=0)
                {
                    canvas.drawBitmap(b1,x-(canvas.getWidth()/2),y-(canvas.getHeight()/2),null);
                }
                if(sX!=0 && sY!=0)
                {
                    canvas.drawBitmap(b2,sX-(canvas.getWidth()/2),sY-(canvas.getHeight()/2),null);
                }
                if(fX!=0 && fY!=0)
                {
                    canvas.drawBitmap(b1,fX - (canvas.getWidth()/2)-animX,fY-(canvas.getHeight()/2)-animY,null);
                    canvas.drawBitmap(b2,fX-(canvas.getWidth()/2),fY-(canvas.getHeight()/2),null);
                }
                animX = animX + scaledX;
                animY = animY + scaledY;
                ourHolder.unlockCanvasAndPost(canvas);
            }

        }
    }

}

