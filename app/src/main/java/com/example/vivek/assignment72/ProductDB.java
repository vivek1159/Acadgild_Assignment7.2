package com.example.vivek.assignment72;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by vivek on 25-04-2016.
 */
public class ProductDB extends SQLiteOpenHelper{

    static SQLiteDatabase db;
    static String TAG = "ProductDB";

    public static String DATABASE_NAME = "Products.db";
    public static int DATABASE_VERSION = 1;
    public static String TABLE_NAME = "PRODUCT_TABLE";
    public static String CREATE_DB = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            " PRODUCT_NAME TEXT PRIMARY KEY )";


    public ProductDB(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB);
        ContentValues values = new ContentValues();
        values.put("PRODUCT_NAME", "DETTOL");
        db.insert(TABLE_NAME, null, values);
        values.put("PRODUCT_NAME", "LIFEBUOY");
        db.insert(TABLE_NAME, null, values);
        values.put("PRODUCT_NAME", "PATANJALI");
        db.insert(TABLE_NAME, null, values);
        values.put("PRODUCT_NAME", "PANTENE");
        db.insert(TABLE_NAME, null, values);
        values.put("PRODUCT_NAME", "DENIM");
        db.insert(TABLE_NAME, null, values);
        values.put("PRODUCT_NAME", "LIGHTER");
        db.insert(TABLE_NAME, null, values);
        values.put("PRODUCT_NAME", "GILETTE");
        db.insert(TABLE_NAME, null, values);
        values.put("PRODUCT_NAME", "GIJOE TOY");
        db.insert(TABLE_NAME, null, values);
        Log.d(TAG, "All values inserted");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL(CREATE_DB);
    }


    public ArrayList<String> getProductData() {
        db = this.getReadableDatabase();
        Log.d(TAG,"in getProductData");
        ArrayList<String> List = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT PRODUCT_NAME FROM " +TABLE_NAME,null);

        if (cursor!=null)
        {
            Log.d(TAG,"cursor not null");
            cursor.moveToFirst();
            do {
                Log.d(TAG,"fetching..");
                List.add(cursor.getString(cursor.getColumnIndex("PRODUCT_NAME")));
            }while (cursor.moveToNext());
            cursor.close();
            db.close();
            return List;
        }
        else {
            Log.d(TAG,"cursor null");
            db.close();
            return null;
        }
    }

    public void open() {
        db = this.getReadableDatabase();
    }
}
