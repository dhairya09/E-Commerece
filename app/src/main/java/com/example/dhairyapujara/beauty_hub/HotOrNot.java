package com.example.dhairyapujara.beauty_hub;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by Dhairya Pujara on 1/29/2016.
 */
public class HotOrNot
{
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "Name";
    public static final String KEY_HOT = "Hotness";

    private static final String DATABASE_NAME = "HotOrNot";
    private static final String TABLE_NAME = "Peoples";
    private static final int DATABASE_VERSION = 1;

    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;




    private static class DbHelper extends SQLiteOpenHelper{

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                        + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + KEY_NAME + " TEXT NOT NULL, "
                        + KEY_HOT + " TEXT NOT NULL);"


            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
             db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
             onCreate(db);
        }

    }
    public HotOrNot(Context c)
    {
        ourContext = c;
    }


    public HotOrNot open() throws SQLException
    {
        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;

    }
    public HotOrNot close(){
        ourHelper.close();
        return this;
    }

    public long dataEntry(String str, String str1)
    {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME,str);
        cv.put(KEY_HOT, str1);
        return ourDatabase.insert(TABLE_NAME,null,cv);
    }
}
