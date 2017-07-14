package com.example.dhairyapujara.beauty_hub;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Dhairya Pujara on 1/28/2016.
 */
public class OurViewClient extends WebViewClient
{
    @Override

    public boolean shouldOverrideUrlLoading(WebView v,String url)
    {
        v.loadUrl(url);
        return true;
    }
}
