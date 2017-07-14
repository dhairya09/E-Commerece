package com.example.dhairyapujara.beauty_hub;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;


public class SimpleBrowser extends Activity implements View.OnClickListener {
    WebView wv;
    EditText url;
    Button go,forw,back,ref,clear;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browser);
        wv = (WebView)findViewById(R.id.web);

        url = (EditText)findViewById(R.id.etURL);
        go = (Button)findViewById(R.id.bGo);
        forw = (Button)findViewById(R.id.fP);
        back = (Button)findViewById(R.id.bP);
        ref = (Button)findViewById(R.id.rP);
        clear = (Button)findViewById(R.id.cH);
        go.setOnClickListener(this);
        forw.setOnClickListener(this);
        back.setOnClickListener(this);
        ref.setOnClickListener(this);
        clear.setOnClickListener(this);

        wv.setWebViewClient(new OurViewClient());
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setLoadWithOverviewMode(true);
        wv.getSettings().getUseWideViewPort();
        try{
            wv.loadUrl("https://www.google.co.in/");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bGo:
                            String str = url.getText().toString();
                            wv.loadUrl(str);
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromInputMethod(url.getWindowToken(),0);
                            break;
            case R.id.fP:   if(wv.canGoForward())
                            wv.goForward();
                            break;
            case R.id.bP:   if(wv.canGoBack())
                            wv.goBack();
                             break;
            case R.id.rP:   wv.reload();
                             break;
            case R.id.cH:   wv.clearHistory();
                            break;
        }
    }
}
