package com.example.dhairyapujara.beauty_hub;

import android.graphics.Bitmap;

/**
 * Created by Dhairya Pujara on 1/21/2016.
 */

    public class Config {

        //Address of our scripts of the CRUD
        public static final String URL_ADD_HENNA="http://192.168.43.157/beauty_hub/create_henna_app.php";

        public static final String URL_ADD="http://192.168.43.157/beauty_hub/create_brand_app.php";
        public static final String URL_ADD_CATEGORY = "http://192.168.43.157/beauty_hub/create_category_app.php";
        public static final String URL_ADD_ITEM ="http://192.168.43.157/beauty_hub/create_item_app.php";
        public static final String URL_ADD_USER = "http://192.168.43.157/beauty_hub/add_user_app.php";
        public static final String URL_VIEW_PRODUCTS = "http://192.168.43.157/beauty_hub/view_table_app.php";

        public static final String URL_USER_LOGIN = "http://192.168.43.157/beauty_hub/login_app.php";

        public static final String KEY_IMAGE = "image";
        //public static final Bitmap KEY_IMAGE = null;
        public static final String KEY_IMG_URL = "http://192.168.43.157/image_upload/IMG_upload_app.php";
        public static final String KEY_IMG_URL_FETCH = "http://192.168.43.157/image_upload/IMG_fetch_app.php?id=";


        public static final String URL_GET_ALL = "http://192.168.43.157/beauty_hub/getAll_brand_app.php";
        public static final String URL_GET_ALL_CAT = "http://192.168.43.157/beauty_hub/getAll_category_app.php";
        public static final String URL_GET_ALL_ITEM = "http://192.168.43.157/beauty_hub/getAll_item_app.php";
        public static final String URL_GET_ALL_GEN = "http://192.168.43.157/beauty_hub/getAll_gender_app.php";
        public static final String URL_GET_ALL_MEN = "http://192.168.43.157/beauty_hub/getAll_men_app.php";
        public static final String URL_GET_ALL_WOMEN = "http://192.168.43.157/beauty_hub/getAll_women_app.php";

        public static final String URL_GET_ITEM_BASED_ON_SUBSUBCAT = "http://192.168.43.157/beauty_hub/getItemsBasedOnCat_app.php";
        public static final String URL_GET_SUBCAT_BASED_ON_CAT = "http://192.168.43.157/beauty_hub/getSubBasedOnCat_app.php";
        public static final String URL_GET_SUBSUBCAT_BASED_ON_SUBCAT = "http://192.168.43.157/beauty_hub/getSubSubBasedOnSubCat_app1.php";


        public static final String URL_GET_EMP = "http://192.168.43.157/beauty_hub/getparticular_brand_app.php";
        public static final String URL_GET_EMP_CAT = "http://192.168.43.157/beauty_hub/getparticular_category_app.php";
    public static final String URL_GET_EMP_ITEM = "http://192.168.43.157/beauty_hub/getparticular_item_app.php";

        public static final String URL_UPDATE_EMP = "http://192.168.43.157/beauty_hub/update_brand_app.php";
        public static final String URL_UPDATE_CAT = "http://192.168.43.157/beauty_hub/update_category_app.php";
        public static final String URL_UPDATE_ITEM = "http://192.168.43.157/beauty_hub/update_item_app.php";

        public static final String URL_DELETE_EMP = "http://192.168.43.157/beauty_hub/delete_brand_app.php";
        public static final String URL_DELETE_CAT = "http://192.168.43.157/beauty_hub/delete_category_app.php";
        public static final String URL_DELETE_ITEM = "http://192.168.43.157/beauty_hub/delete_item_app.php";

        //Keys that will be used to send the request to php scripts
        public static final String KEY_EMP_ID = "id";
        public static final String KEY_EMP_CNAME = "c_name";
        public static final String KEY_EMP_GNAME = "g_name";
        public static final String KEY_EMP_SCNAME = "sc_name";
    public static final String KEY_EMP_SSCNAME = "ssc_name";
        public static final String KEY_EMP_NAME = "menu_name";
        public static final String KEY_EMP_POS = "position";
        public static final String KEY_EMP_VIS = "visible";
        public static final String KEY_EMP_UNAME = "u_name";
         public static final String KEY_EMP_PWD = "u_pwd";

        //JSON Tags
        public static final String TAG_JSON_ARRAY="result";
        public static final String TAG_ID = "id";
        public static final String TAG_NAME = "name";
        public static final String TAG_SUBCAT = "SubCategory";
        public static final String TAG_SUBSUBCAT = "SubSubCategory";
        public static final String TAG_CAT = "Category";
        public static final String TAG_GENDER = "Gender";
        public static final String TAG_POS = "pos";
        public static final String TAG_VIS = "vis";
        public static final String TAG_KEY = "key";
        public static final String TAG_DESC = "Description";
        public static final String TAG_PRICE = "Price";
        public static final String TAG_IMG_NAME = "Image_Name";
        public static final String TAG_IMG_PATH = "Image_Path";

        public static final String TAG_GENDER_NAME = "g_name";
        /*public static final String TAG_CATEGORY_NAME = "c_name";
        public static final String TAG_BRAND_NAME = "b_name";
        public static final String TAG_ITEM_NAME = "i_name";*/

        //employee id to pass with intent
        public static final String EMP_ID = "emp_id";
    }

