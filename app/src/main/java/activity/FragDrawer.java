package activity;

/**
 * Created by Dhairya Pujara on 2/29/2016.
 */
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.dhairyapujara.beauty_hub.Config;
import com.example.dhairyapujara.beauty_hub.DisplayItem;
import com.example.dhairyapujara.beauty_hub.DisplayProduct;
import com.example.dhairyapujara.beauty_hub.DisplayProducts;
import com.example.dhairyapujara.beauty_hub.R;
import com.example.dhairyapujara.beauty_hub.RequestHandler;
import com.example.dhairyapujara.beauty_hub.SideMenu;
import com.example.dhairyapujara.beauty_hub.SubCategoryExpandableList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import adapter.ExpandListAdapter;
import adapter.ExpandableListAdapter;
import adapter.NavigationDrawerAdapter;
import model.NavDrawerItem;

public class FragDrawer extends Fragment {

    private static String TAG = FragmentDrawer.class.getSimpleName();

    //private RecyclerView recyclerView;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private NavigationDrawerAdapter adapter;
    private View containerView;
    private static String[] titles = null;
    private FragDrawer.FragmentDrawerListener drawerListener;
    //View view_Group;
    /*JSONObject JSONGender = new JSONObject();
    JSONObject JSONCategory = new JSONObject();
    JSONObject EverythingJSON = new JSONObject();
    JSONArray j = new JSONArray();*/

    ExpandListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    List<String> Men_list = new ArrayList<String>();
    List<String> Women_list = new ArrayList<String>();

    List<String> send = new ArrayList<String>();

    public FragDrawer() {

    }

    public void setDrawerListener(FragDrawer.FragmentDrawerListener listener) {
        this.drawerListener = listener;
    }

    /*public List<NavDrawerItem> getData() {
        List<NavDrawerItem> data = new ArrayList<>();


        // preparing navigation drawer items
        for (int i = 0; i < titles.length; i++) {
            NavDrawerItem navItem = new NavDrawerItem();
            navItem.setTitle(titles[i]);
            data.add(navItem);
            listDataHeader.add(titles[i]);
        }
        return data;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // drawer labels
        titles = getActivity().getResources().getStringArray(R.array.nav_drawer_labels);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflating view layout
        View layout = inflater.inflate(R.layout.fragment_nav_drawer, container, false);
        //recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);
        expListView = (ExpandableListView)layout.findViewById(R.id.Expand);




                    //adapter = new NavigationDrawerAdapter(getActivity(), getData());





                listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        for (int i = 0; i < titles.length; i++) {
            NavDrawerItem navItem = new NavDrawerItem();
            navItem.setTitle(titles[i]);

            listDataHeader.add(titles[i]);
        }
        Log.d("Main Categories:", String.valueOf(listDataHeader));
        getMen();
        getWomen();
        Log.d("Sub Categories:", String.valueOf(listDataChild));

        //listAdapter = new ExpandListAdapter(this, listDataHeader, listDataChild);
        //expListView.setAdapter(listAdapter);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            /*@Override
            public void onClick(View view, int position) {
                drawerListener.onDrawerItemSelected(view, position);
                mDrawerLayout.closeDrawer(containerView);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));*/

        return layout;
    }

    private void getWomen()
    {
        class Women extends AsyncTask<String,Void,String>
        {

            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();

            }
            @Override
            protected void onPostExecute(String s)
            {
                super.onPostExecute(s);
                Log.d("Women Categories:", s);
                showWomen(s);


            }
            @Override
            protected String doInBackground(String... strings) {
                String url = strings[0];
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(url);

                return s;
            }
        }
        Women i = new Women();
        i.execute(Config.URL_GET_ALL_WOMEN);
    }

    private void showWomen(String s) {

        JSONArray jaar = null;
        JSONObject json = null;
        try {
            json = new JSONObject(s);
            jaar = json.getJSONArray(Config.TAG_JSON_ARRAY);
            for (int i = 0; i < jaar.length(); i++) {
                JSONObject c = jaar.getJSONObject(i);
                String name = c.getString(Config.TAG_NAME);
                Women_list.add(name);

            }

            Log.d("Women List:", String.valueOf(Women_list));

            listDataChild.put(listDataHeader.get(2), Women_list);

            Log.d("Women Child:", String.valueOf(listDataChild));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        listAdapter = new ExpandListAdapter(getActivity(), listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                Toast.makeText(getActivity(),
                        "Group Clicked " + listDataHeader.get(groupPosition),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                /*Toast.makeText(
                        getActivity(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });*/
                String gen = listDataHeader.get(groupPosition);
                String cat = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
                //MenCat(gen, cat);

                Cat(gen, cat);
                return false;
            }


        });
    }

    private void Cat(final String gen, final String cat)
    {
        class SubCatItem extends AsyncTask<String,Void,String>
        {
            ProgressDialog loading;
            @Override
            protected void onPreExecute()
            {
                loading = ProgressDialog.show(getActivity(),"Loading","Wait",true,true);
                super.onPreExecute();
            }
            @Override
            protected String doInBackground(String... strings)
            {
                String url = strings[0];
                HashMap<String,String> params = new HashMap<String,String>();
                params.put(Config.KEY_EMP_GNAME,gen);
                params.put(Config.KEY_EMP_CNAME,cat);
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(url, params);
                return res;
            }
            @Override
            protected void onPostExecute(String s)
            {
                if(loading.isShowing())
                    loading.dismiss();
                super.onPostExecute(s);
                Log.d("result",s);
                Dialog d = new Dialog(getActivity());
                TextView tv = new TextView(getActivity());
                d.setTitle("hacked");
                tv.setText(s);
                d.setContentView(tv);
                d.show();


                DisplaySubCat(s,gen,cat);



            }

        }

        SubCatItem s = new SubCatItem();
        s.execute(Config.URL_GET_SUBCAT_BASED_ON_CAT);
    }

    private void DisplaySubCat(String s,String gen,String cat)
    {
        String pass = null;
        /*putinJSON(JSONGender,gen);
        putinJSON(JSONCategory, cat);
        try {
            EverythingJSON.put("Gender", JSONGender);
            EverythingJSON.put("Category", JSONCategory);
                    } catch (JSONException e) {
            e.printStackTrace();
        }*/

        pass = ""+s+"  "+gen+"  "+cat;

        Log.d("Pinal", String.valueOf(pass));
        Intent i = new Intent(getActivity(), SubCategoryExpandableList.class);
        i.putExtra("data",pass);
        startActivity(i);

    }

    /*private void putinJSON(JSONObject json, String value)
    {
        try {
            json.put("key",value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/


    private void MenCat(final String g,final String s)
    {
        Log.d("Which Category:", s);
        Log.d("Which Gender:", g);
        class MenItem extends AsyncTask<String,Void,String>
        {
            ProgressDialog loading;
            protected void onPreExecute()
            {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(),"Loading","Please Wait",true,true);

            }
            @Override
            protected String doInBackground(String... strings)
            {
                String url = strings[0];
                HashMap<String,String> params = new HashMap<String,String>();
                params.put(Config.KEY_EMP_GNAME,g);
                params.put(Config.KEY_EMP_CNAME,s);
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(url, params);
                return res;
            }
            protected void onPostExecute(String rs)
            {
                super.onPostExecute(rs);
                if(loading.isShowing())
                    loading.dismiss();
                Log.d("result",rs);
                Dialog d = new Dialog(getActivity());
                TextView tv = new TextView(getActivity());
                d.setTitle("hacked");
                tv.setText(rs);
                d.setContentView(tv);
                d.show();
                DisplayItems(rs);


            }
        }

        MenItem mi = new MenItem();
        mi.execute(Config.URL_GET_ITEM_BASED_ON_SUBSUBCAT);
    }

    private void DisplayItems(String rs)
    {
        Intent i = new Intent(getActivity(), DisplayItem.class);
        i.putExtra("data",rs);
        startActivity(i);
    }


    private void getMen()
    {

            class Men extends AsyncTask<String,Void,String>
            {

                @Override
                protected void onPreExecute()
                {
                    super.onPreExecute();

                }
                @Override
                protected void onPostExecute(String s)
                {
                    super.onPostExecute(s);
                    Log.d("Men Categories:", s);
                    showMen(s);


                }
                @Override
                protected String doInBackground(String... strings) {
                    String url = strings[0];
                    RequestHandler rh = new RequestHandler();
                    String s = rh.sendGetRequest(url);

                    return s;
                }
            }
            Men i = new Men();
            i.execute(Config.URL_GET_ALL_MEN);

    }

    private void showMen(String s)
    {
        JSONArray jaar = null;

        try {
            JSONObject json = new JSONObject(s);
            jaar = json.getJSONArray(Config.TAG_JSON_ARRAY);
            for(int i=0;i<jaar.length();i++)
            {
                JSONObject c = jaar.getJSONObject(i);
                String name = c.getString(Config.TAG_NAME);
                Men_list.add(name);
            }
            Log.d("Men List:", String.valueOf(Men_list));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listDataChild.put(listDataHeader.get(1), Men_list);
        Log.d("Men Child:", String.valueOf(listDataChild));

    }


    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }


    }

    public interface FragmentDrawerListener {
        public void onDrawerItemSelected(View view, int position);
    }
}
