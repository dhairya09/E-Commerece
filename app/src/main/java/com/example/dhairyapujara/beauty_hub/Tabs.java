package com.example.dhairyapujara.beauty_hub;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;


public class Tabs extends Activity implements View.OnClickListener {
    TabHost th;
    Button b_start,b_stop,b_addTab;
    TextView tv_disp;
    long start,stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);
        b_start = (Button)findViewById(R.id.sW);
        b_stop = (Button)findViewById(R.id.StW);
        b_addTab = (Button)findViewById(R.id.addTab);
        tv_disp = (TextView)findViewById(R.id.SResult);
        start = 0;

        b_start.setOnClickListener(this);
        b_stop.setOnClickListener(this);
        b_addTab.setOnClickListener(this);

        th = (TabHost)findViewById(R.id.tabHost);
        th.setup();

        TabHost.TabSpec specs = th.newTabSpec("tag1");
        specs.setContent(R.id.tab1);
        specs.setIndicator("Stop Watch");
        th.addTab(specs);

        specs = th.newTabSpec("tag2");
        specs.setContent(R.id.tab2);
        specs.setIndicator("Tab 2");
        th.addTab(specs);

        specs = th.newTabSpec("tag3");
        specs.setContent(R.id.tab3);
        specs.setIndicator("Add a Tab");
        th.addTab(specs);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.sW:
                            start = System.currentTimeMillis();
                            break;
            case R.id.StW:
                            stop = System.currentTimeMillis();
                            if(start!=0)
                            {
                                long res = stop - start;
                                int millis = (int)res;
                                int seconds  = millis/1000;
                                int minutes = seconds/60;
                                millis = millis%1000;
                                seconds = seconds%60;

                                tv_disp.setText(String.format("%d:%02d:%02d",minutes,seconds,millis ));
                            }
                            break;
            case R.id.addTab:
                TabHost.TabSpec ourSpec = th.newTabSpec("tag1");
                ourSpec.setContent(new TabHost.TabContentFactory() {
                    @Override
                    public View createTabContent(String s) {
                        TextView tv = new TextView(Tabs.this);
                        tv.setText("u have created a new tab");
                        return (tv);
                    }
                });
                ourSpec.setIndicator("New");
                th.addTab(ourSpec);


                            break;
        }
    }
}
